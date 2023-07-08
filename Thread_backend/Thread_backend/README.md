# Thread_backend

Backend using Springboot for Thread , a Social Media Platform (Web Application).

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)

### Steps to run

1. Build the project using `mvn clean install`
2. Run using `mvn spring-boot:run`
3. The web application is accessible via localhost:8080
4. Use username :'admin' and password :"Admin@1234" to login.

### UNAUTHORISED PATHS

- "localhost:8080/register"
- "localhost:8080/getRoles"
- "localhost:8080/authenticate"
- "localhost:8080/contactus/create”

### FOR FILE UPLOAD

_**In {RelativePath}/Thread_Db_Connect_personal/src/main/java/com/example/thread/Post/service/PostServiceImpl.java File
, Create a folder("MyImages") in public dir of React App.
And Add the Full Path to FOLDER_PATH**_

# Controllers and APIs

## 1. User Controller

- Create User (All given Four Parameters required )

```
Restrictions:

Email = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"
                 * ------------------------
                This expression matches email addresses, and checks that they are of the
                proper form.
                It checks to ensure the top level domain is between 2 and 4 characters long,
                but does not check the specific domain against a list (especially since
                there are so many of them now).


Password = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}"
                * ------------------------
                * At least 8 chars
                * Contains at least one digit
                * Contains at least one lower alpha char and one upper alpha char
                * Contains at least one char within a set of special chars (@#%$^ etc.)
                * Does not contain space, tab, etc.


UserName = "[A-Za-z0-9_]+" = Alphabets Only + "_"

UserFullName = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"= Alphabets Only + "_"+ Space

Post : localhost:8080/register

Request Body

{"userName":"demo","userFullName":"Demo User","userPassword":"Demo@1234","email":"Demo@gmail.com"}

RESPONSE

{
   "status": "success",
   "message": "User created!",
   "data": [
       {
           "userName": "demo",
           "userFullName": "Demo User",
           "userPassword": "$2a$10$PK8Qa/zh/SkBkJrEYZsqqu5KwJl1dAPZBHjiArI.C6NYl.bIpGxwS",
           "email": "Demo@gmail.com",
           "city": null,
           "gender": null,
           "profileImage": "defaultProfile.jpg",
           "role": [
               {
                   "roleName": "User",
                   "roleDescription": "Default role for newly created record"
               }
           ],
           "connector": null,
           "dob": null
       }
   ],
   "count": 1
}
```

- Authenticate User (JWT Controller)

```
User connector are made null explicitily to avoid loop of connections
Post : localhost:8080/authenticate

Request Body

{
    "userName":"demo",
    "userPassword":"Demo@1234"
}

RESPONSE

{
    "status": "success",
    "message": "Authenticated Successfully",
    "data": [
        {
            "user": {
                "userName": "demo",
                "userFullName": "Demo User",
                "userPassword": "$2a$10$PK8Qa/zh/SkBkJrEYZsqqu5KwJl1dAPZBHjiArI.C6NYl.bIpGxwS",
                "email": "Demo@gmail.com",
                "city": null,
                "gender": null,
                "profileImage": "defaultProfile.jpg",
                "role": [
                    {
                        "roleName": "User",
                        "roleDescription": "Default role for newly created record"
                    }
                ],
                "connector": [],
                "dob": null
            },
            "jwtToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA"
        }
    ],
    "count": 1
}
```

- Update User Info

```
RESTRICTIONS
// ! UserEmail & Username can't be changed.
UserName
UserPassword
UserEmail
UserFullName
(as previous)
dob = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$" = in dd/mm/yyyy (can be null)
city and gender = "^[a-zA-Z]+(([' ][a-zA-Z ])?[a-zA-Z]*)*$" = Alphabets with space only  (can be null)
profileImage = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)" Extensions given only (can be null)



Post : localhost:8080/user/update

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request FORM DATA

user:{ "userName": "demo","userFullName":"Demo SINGH","email": "Demo@gmail.com","userPassword":"Demo@1234","city":"Lucknow","gender":"Male","dob":"07/03/2004"}

file:java.jpg

RESPONSE

{
    "status": "success",
    "message": "User updated Successfully !",
    "data": [
        {
            "userName": "demo",
            "userFullName": "Demo SINGH",
            "userPassword": "$2a$10$M1U02IsyQStVpKEm2cxggOUsmYbA1hNL3Cg2q5jAqZJmw1NvUd7/u",
            "email": "Demo@gmail.com",
            "city": "Lucknow",
            "gender": "Male",
            "profileImage": "java___bJ2JS.jpg",
            "role": [
                {
                    "roleName": "User",
                    "roleDescription": "Default role for newly created record"
                }
            ],
            "connector": null,
            "dob": "07/03/2004"
        }
    ],
    "count": 1
}
```

- getUserByDetails for forgot password

```
RESTRICTIONS

Get : localhost:8080/get/user

Headers

Request Body

{
    "userName":"Mohit_lcs32",
    "userFullName":"Mohit Raghav",
    "email":"lcs2022032@iiitl.ac.in"
}

RESPONSE

{
    "status": "success",
    "message": "User Exists with details !",
    "data": [
        {
            "userName": "Mohit_lcs32",
            "userFullName": "Mohit Raghav",
            "userPassword": "$2a$10$vZhMyW/8mBkpNvtCwMuG1eQVK/3JC9O18iVqL5jqtWo.41euOklA2",
            "email": "lcs2022032@iiitl.ac.in",
            "city": "Lucknow",
            "gender": "Male",
            "profileImage": "mohit.jpeg",
            "role": [
                {
                    "roleName": "Admin",
                    "roleDescription": "Admin role"
                }
            ],
            "connector": null,
            "dob": "04/03/2004"
        }
    ],
    "count": 1
}
```

- Change Password

```
RESTRICTIONS

UserPassword

Post : localhost:8080/changepassword

Headers

Request Body

{
    "userName":"Mohit_lcs32",
    "userFullName":"Mohit Raghav",
    "email":"lcs2022032@iiitl.ac.in",
    "userPassword":"Mo@123456"
}

RESPONSE

{
    "status": "success",
    "message": "User created!",
    "data": [
        {
            "userName": "Mohit_lcs32",
            "userFullName": "Mohit Raghav",
            "userPassword": "$2a$10$BVL7qDk/ovhLZS3d8V8hUuNhmyCTvpz0Ga42GAIUxYmMchyaYKMXm",
            "email": "lcs2022032@iiitl.ac.in",
            "city": "Lucknow",
            "gender": "Male",
            "profileImage": "mohit.jpeg",
            "role": [
                {
                    "roleName": "Admin",
                    "roleDescription": "Admin role"
                }
            ],
            "connector": null,
            "dob": "04/03/2004"
        }
    ],
    "count": 1
}
```

## 2. POST Controller

- Create Post

```
Post : localhost:8080/posts/

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request FORM DATA

post:{"description":"Demo post description"}
file: post_demo.jpg

RESPONSE

{
    "status": "success",
    "message": "Post Added Successfully",
    "data": [
        {
            "postId": "demo___QGS43",
            "userName": "demo",
            "userFullName": "Demo SINGH",
            "profileImage": "java___9mtSt.jpg",
            "description": "Demo post description",
            "postImage": "post_demo___marh2.jpg",
            "creationTime": " Just Now",
            "likes": 0,
            "comments": null
        }
    ],
    "count": 1
}
```

- Get Post Feed

```
// ! Get All Posts except Logged in User
Get : localhost:8080/posts/feed

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request

RESPONSE

{
    "status": "success",
    "message": "All Posts fetched Successfully",
    "data": [
        {
            "postId": "post0",
            "userName": "Manish_Rawat",
            "userFullName": "Manish Rawat",
            "profileImage": "user0.jpg",
            "description": "जय श्री रामश्रद्धा, बलिदान, भाव और बंधन इनका आदर राम भक्त कर पाते है ,\n\nवरना आजकल के पढ़े लिखे राम राम बोलने में शरमाते है।।\n\nजय श्री राम",
            "postImage": "post0.jpg",
            "creationTime": " Just Now",
            "likes": 13,
            "comments": [
                {
                    "commentId": 49,
                    "commentText": "जय श्री राम",
                    "userName": "karthik_bannu",
                    "userFullName": "Karthik Akshaj",
                    "profileImage": "user1.jpg",
                    "postId": "post0",
                    "creationTime": " Just Now"
                },
                {
                    "commentId": 50,
                    "commentText": "Jay Shree Raam",
                    "userName": "Umesh_lcs52",
                    "userFullName": "Umesh Singh Verma",
                    "profileImage": "umesh.jpeg",
                    "postId": "post0",
                    "creationTime": " Just Now"
                },
                {
                    "commentId": 51,
                    "commentText": "जय श्री राम",
                    "userName": "money_heist",
                    "userFullName": "Professor",
                    "profileImage": "user3.jpg",
                    "postId": "post0",
                    "creationTime": "2 min ago"
                }
            ]
        },
        {
            "postId": "post3",
            "userName": "money_heist",
            "userFullName": "Professor",
            "profileImage": "user3.jpg",
            "description": "First time is always so special, unique. But the last time is beyond comparison. It is priceless. But people don't normally know it is their last time.\n\nIn this world, everything is governed by balance. There's what you stand to gain and what you stand to lose. And when you think you've got nothing to lose, you become overconfident.",
            "postImage": "post3.jpg",
            "creationTime": " Just Now",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post1",
            "userName": "karthik_bannu",
            "userFullName": "Karthik Akshaj",
            "profileImage": "user1.jpg",
            "description": "Squid Games \n\n Hundreds of cash-strapped contestants accept an invitation to compete in children's games for a tempting prize, but the stakes are deadly.",
            "postImage": "post1.jpg",
            "creationTime": " Just Now",
            "likes": 3,
            "comments": []
        },
        {
            "postId": "post6",
            "userName": "Mohit_lcs32",
            "userFullName": "Mohit Raghav",
            "profileImage": "mohit.jpeg",
            "description": "Welcome All,Here is our Project Thread, a social media platform. ",
            "postImage": "logo.jpg",
            "creationTime": "2 min ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post2",
            "userName": "Slumber_420",
            "userFullName": "Praveen Kumar",
            "profileImage": "user2.jpg",
            "description": "Yes, I am a criminal. My crime is that of curiosity. My crime is that of outsmarting you, something that you will never forgive me for.\n I am a hacker, and this is my manifesto. You may stop this individual, but you can't stop us all… after all, we're all alike.",
            "postImage": "post2.jpeg",
            "creationTime": "6 days ago",
            "likes": 4,
            "comments": [
                {
                    "commentId": 54,
                    "commentText": "Ya..well said",
                    "userName": "Umesh_lcs52",
                    "userFullName": "Umesh Singh Verma",
                    "profileImage": "umesh.jpeg",
                    "postId": "post2",
                    "creationTime": "2 min ago"
                }
            ]
        },
        {
            "postId": "post11",
            "userName": "Sunil_lcs48",
            "userFullName": "Sunil Tirwal",
            "profileImage": "sunil.jpg",
            "description": "Carry out a random act of kindness, with no expectation of reward, safe in the knowledge that one day someone might do the same for you.",
            "postImage": null,
            "creationTime": "7 days ago",
            "likes": 2,
            "comments": []
        },
        {
            "postId": "post4",
            "userName": "asur_official",
            "userFullName": "Mukti Giver",
            "profileImage": "user4.jpeg",
            "description": "Limitless Darkness Is The Ultimate Truth, Attachment Is Suffering,Kindness Is Cruelty And The End Itself Is The Beginning.",
            "postImage": "post4.jpg",
            "creationTime": "1 months ago",
            "likes": 1,
            "comments": []
        },
        {
            "postId": "post13",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": null,
            "postImage": "3.jpg",
            "creationTime": "3 months ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post9",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": "This is only Text Post. ",
            "postImage": null,
            "creationTime": "3 months ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post12",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": "दोस्त है मेरा बहारों जैसा, दिल है उसका दिलदारों जैसा, बहोत दोस्त नही रखते हम मगर, मेरा एक ही दोस्त है हजारों जैसा।",
            "postImage": "15.jpg",
            "creationTime": "6 months ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post10",
            "userName": "Umesh_lcs52",
            "userFullName": "Umesh Singh Verma",
            "profileImage": "umesh.jpeg",
            "description": "This is only Text Post. How are you guys?  ",
            "postImage": null,
            "creationTime": "1 years ago",
            "likes": 1,
            "comments": [
                {
                    "commentId": 52,
                    "commentText": "Well Bhai",
                    "userName": "money_heist",
                    "userFullName": "Professor",
                    "profileImage": "user3.jpg",
                    "postId": "post10",
                    "creationTime": "2 min ago"
                },
                {
                    "commentId": 53,
                    "commentText": "Good Bro",
                    "userName": "marvel_official",
                    "userFullName": "Marvels",
                    "profileImage": "user5.jpg",
                    "postId": "post10",
                    "creationTime": "2 min ago"
                }
            ]
        },
        {
            "postId": "post5",
            "userName": "marvel_official",
            "userFullName": "Marvels",
            "profileImage": "user5.jpg",
            "description": "At Some Point, We All Have to Choose, Between What the World Wants You to Be, and Who You Are.",
            "postImage": "post5.png",
            "creationTime": "2 years ago",
            "likes": 0,
            "comments": [
                {
                    "commentId": 55,
                    "commentText": "Ya..well said",
                    "userName": "asur_official",
                    "userFullName": "Mukti Giver",
                    "profileImage": "user4.jpeg",
                    "postId": "post5",
                    "creationTime": "2 min ago"
                }
            ]
        },
        {
            "postId": "post8",
            "userName": "axios_iiitl",
            "userFullName": "Axios Technical Society",
            "profileImage": "Axios.jpg",
            "description": "We Axios , Technical Society of IIITL will always be ready to help you . Hoping to see you soon.",
            "postImage": "post_axios.jpg",
            "creationTime": "4 years ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post7",
            "userName": "iiitl_official",
            "userFullName": "IIIT LUCKNOW",
            "profileImage": "iiitlProfile.png",
            "description": "IIIT Lucknow invites you to our Family.",
            "postImage": "postIIITL.jpg",
            "creationTime": "6 years ago",
            "likes": 1,
            "comments": []
        }
    ],
    "count": 14
}
```

- Get Post By UserName

```
Get : localhost:8080/posts/user/Harshit_lcs21
// posts/user/{userName}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE

{
    "status": "success",
    "message": "All Posts of a User fetched Successfully",
    "data": [
        {
            "postId": "post13",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": null,
            "postImage": "3.jpg",
            "creationTime": "3 months ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post9",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": "This is only Text Post. ",
            "postImage": null,
            "creationTime": "3 months ago",
            "likes": 0,
            "comments": []
        },
        {
            "postId": "post12",
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg",
            "description": "दोस्त है मेरा बहारों जैसा, दिल है उसका दिलदारों जैसा, बहोत दोस्त नही रखते हम मगर, मेरा एक ही दोस्त है हजारों जैसा।",
            "postImage": "15.jpg",
            "creationTime": "6 months ago",
            "likes": 0,
            "comments": []
        }
    ],
    "count": 3
}
```

- **ONLY THE Owner USER** i.e Logged In User Can get and delete His postById.

```
Get : localhost:8080/posts/demo___QGS43
// posts/{postId}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE
{
    "status": "success",
    "message": "Post fetched Successfully",
    "data": [
        {
            "postId": "demo___QGS43",
            "user": {
                "userName": "demo",
                "userFullName": "Demo SINGH",
                "userPassword": "$2a$10$rGO62d/yKPM/g0990IxluOgaY3pM00r3fOzjkDFvJ6fih53q9TXty",
                "email": "Demo@gmail.com",
                "city": "Lucknow",
                "gender": "Male",
                "profileImage": "java___9mtSt.jpg",
                "role": [
                    {
                        "roleName": "User",
                        "roleDescription": "Default role for newly created record"
                    }
                ],
                "connector": [],
                "dob": "07/03/2004"
            },
            "description": "Demo post description",
            "postImage": "post_demo___marh2.jpg",
            "creationTime": "2023-06-26T05:21:39",
            "comments": [],
            "likes": []
        }
    ],
    "count": 1
}
```

- Delete Post by Id

```
Delete : localhost:8080/posts/demo___QGS43
// posts/{postId}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE
{
    "status": "success",
    "message": "Post with ID demo___QGS43 has been deleted.",
    "data": [],
    "count": 0
}
```

## 3. LIKE CONTROLLER

- Like the Post

```
post : localhost:8080/like/post0
// like/{postId}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request

RESPONSE
{
    "status": "success",
    "message": "Liked the Post",
    "data": [
        14
    ],
    "count": 1
}
```

- UnLike the Post

```
Delete : localhost:8080/unlike/post0
// unlike/{postId}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE
{
    "status": "success",
    "message": "UnLiked the Post",
    "data": [
        13
    ],
    "count": 1
}
```

- Likes On A Post by PostId

```
Get : localhost:8080/likes/post/post2
// likes/post/{postId}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE

{
    "status": "success",
    "message": "Number of Post likes.",
    "data": [
        4
    ],
    "count": 1
}
```

- User Liked Posts by Auth

```
Get : localhost:8080/likes/user
// likes/user

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE

{
    "status": "success",
    "message": "Posts liked By User Returned",
    "data": [],
    "count": 0
}


Getting posts liked by User (money_heist) with Appropriate Auth Token

{
    "status": "success",
    "message": "Posts liked By User Returned",
    "data": [
        {
            "postId": "post0",
            "userName": "Manish_Rawat",
            "userFullName": "Manish Rawat",
            "profileImage": "user0.jpg",
            "description": "जय श्री रामश्रद्धा, बलिदान, भाव और बंधन इनका आदर राम भक्त कर पाते है ,\n\nवरना आजकल के पढ़े लिखे राम राम बोलने में शरमाते है।।\n\nजय श्री राम",
            "postImage": "post0.jpg",
            "creationTime": " Just Now",
            "likes": 13,
            "comments": [
                {
                    "commentId": 49,
                    "commentText": "जय श्री राम",
                    "userName": "karthik_bannu",
                    "userFullName": "Karthik Akshaj",
                    "profileImage": "user1.jpg",
                    "postId": "post0",
                    "creationTime": " Just Now"
                },
                {
                    "commentId": 50,
                    "commentText": "Jay Shree Raam",
                    "userName": "Umesh_lcs52",
                    "userFullName": "Umesh Singh Verma",
                    "profileImage": "umesh.jpeg",
                    "postId": "post0",
                    "creationTime": " Just Now"
                },
                {
                    "commentId": 51,
                    "commentText": "जय श्री राम",
                    "userName": "money_heist",
                    "userFullName": "Professor",
                    "profileImage": "user3.jpg",
                    "postId": "post0",
                    "creationTime": "18 min ago"
                }
            ]
        },
        {
            "postId": "post1",
            "userName": "karthik_bannu",
            "userFullName": "Karthik Akshaj",
            "profileImage": "user1.jpg",
            "description": "Squid Games \n\n Hundreds of cash-strapped contestants accept an invitation to compete in children's games for a tempting prize, but the stakes are deadly.",
            "postImage": "post1.jpg",
            "creationTime": " Just Now",
            "likes": 3,
            "comments": []
        },
        {
            "postId": "post4",
            "userName": "asur_official",
            "userFullName": "Mukti Giver",
            "profileImage": "user4.jpeg",
            "description": "Limitless Darkness Is The Ultimate Truth, Attachment Is Suffering,Kindness Is Cruelty And The End Itself Is The Beginning.",
            "postImage": "post4.jpg",
            "creationTime": "1 months ago",
            "likes": 1,
            "comments": []
        },
        {
            "postId": "post7",
            "userName": "iiitl_official",
            "userFullName": "IIIT LUCKNOW",
            "profileImage": "iiitlProfile.png",
            "description": "IIIT Lucknow invites you to our Family.",
            "postImage": "postIIITL.jpg",
            "creationTime": "6 years ago",
            "likes": 1,
            "comments": []
        }
    ],
    "count": 4
}
```

## 4. COMMENT Controller

- Create A Comment At Post

```
Post : localhost:8080/post/post0/comment
// /post/{postId}/comment

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request Body

{
    "commentText":"i am demo user. this is demo comment. "
}

RESPONSE

{
    "status": "success",
    "message": "Comment created",
    "data": [
        {
            "commentId": 50,
            "commentText": "Jay Shree Raam",
            "userName": "Umesh_lcs52",
            "userFullName": "Umesh Singh Verma",
            "profileImage": "umesh.jpeg",
            "postId": "post0",
            "creationTime": " Just Now"
        },
        {
            "commentId": 49,
            "commentText": "जय श्री राम",
            "userName": "karthik_bannu",
            "userFullName": "Karthik Akshaj",
            "profileImage": "user1.jpg",
            "postId": "post0",
            "creationTime": " Just Now"
        },
        {
            "commentId": 56,
            "commentText": "i am demo user. this is demo comment. ",
            "userName": "demo",
            "userFullName": "Demo User",
            "profileImage": "defaultProfile.jpg",
            "postId": "post0",
            "creationTime": " Just Now"
        },
        {
            "commentId": 51,
            "commentText": "जय श्री राम",
            "userName": "money_heist",
            "userFullName": "Professor",
            "profileImage": "user3.jpg",
            "postId": "post0",
            "creationTime": "7 min ago"
        }
    ],
    "count": 4
}
```

- Get Comments on Post

```
Get :localhost:8080/post/post2/comment
// /post/{postId}/comment

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE

{
    "status": "success",
    "message": "post comments fetched",
    "data": [
        {
            "commentId": 54,
            "commentText": "Ya..well said",
            "userName": "Umesh_lcs52",
            "userFullName": "Umesh Singh Verma",
            "profileImage": "umesh.jpeg",
            "postId": "post2",
            "creationTime": "9 min ago"
        }
    ],
    "count": 1
}
```

- Get Comment By ID only by owner

```
Get :localhost:8080/comment/56
// /comment/{comment_id}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE

{
    "status": "success",
    "message": "Comment fetched",
    "data": [
        {
            "commentId": 56,
            "commentText": "i am demo user. this is demo comment. ",
            "userName": "demo",
            "userFullName": "Demo User",
            "profileImage": "defaultProfile.jpg",
            "postId": "post0",
            "creationTime": "4 min ago"
        }
    ],
    "count": 1
}
```

- Delete Comment By ID only by owner

```
Delete :localhost:8080/comment/56
// /comment/{comment_id}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA

Request


RESPONSE
{
    "status": "success",
    "message": "Comment deleted",
    "data": [
        {
            "commentId": 50,
            "commentText": "Jay Shree Raam",
            "userName": "Umesh_lcs52",
            "userFullName": "Umesh Singh Verma",
            "profileImage": "umesh.jpeg",
            "postId": "post0",
            "creationTime": " Just Now"
        },
        {
            "commentId": 49,
            "commentText": "जय श्री राम",
            "userName": "karthik_bannu",
            "userFullName": "Karthik Akshaj",
            "profileImage": "user1.jpg",
            "postId": "post0",
            "creationTime": " Just Now"
        },
        {
            "commentId": 51,
            "commentText": "जय श्री राम",
            "userName": "money_heist",
            "userFullName": "Professor",
            "profileImage": "user3.jpg",
            "postId": "post0",
            "creationTime": "12 min ago"
        }
    ],
    "count": 3
}
```

## 5. CONNECTIONS Controller

- Follow a User

```
Post : localhost:8080/follow/user/admin
// follow/user/{userName}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA


Request


RESPONSE

{
    "status": "success",
    "message": "Connection Added!",
    "data": [
        {
            "userName": "admin",
            "userFullName": "Admin",
            "profileImage": "logo.jpg"
        }
    ],
    "count": 1
}
```

- Unfollow A User

```
Delete : localhost:8080/unfollow/user/admin
// unfollow/user/{userName}

Headers (demo)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkZW1vIiwiZXhwIjoxNjg3NzU1MDI5LCJpYXQiOjE2ODc3MzcwMjl9.tU_jOWIEZhbFAwS93ln9SAW13KcWZCkpzz3DVGeqbZvR5Zr8PXvW3p-37mdrwgvhR0cZTABvP5FxCcA96ht9nA


Request FORM DATA


RESPONSE

{
    "status": "success",
    "message": "Connection Removed!",
    "data": [],
    "count": 0
}
```

- GET All Connections

```
Get : localhost:8080/connections


Headers (karthik_bannu)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrYXJ0aGlrX2Jhbm51IiwiZXhwIjoxNjg3NzU3NjI3LCJpYXQiOjE2ODc3Mzk2Mjd9._wBXfotQF4-U5UDCn1kyvx8re2J1ZpQ8WolXKLnf4iVOWjPvJG5YGmSapO9MCtmlhzumoYAOIBrdY1jBUYTiDg

Request


RESPONSE

{
    "status": "success",
    "message": "Fetched All Connections",
    "data": [
        {
            "userName": "money_heist",
            "userFullName": "Professor",
            "profileImage": "user3.jpg"
        },
        {
            "userName": "Slumber_420",
            "userFullName": "Praveen Kumar",
            "profileImage": "user2.jpg"
        }
    ],
    "count": 2
}
```

- GET All NON-Connections

```
Get : localhost:8080/nonconnections


Headers (karthik_bannu)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJrYXJ0aGlrX2Jhbm51IiwiZXhwIjoxNjg3NzU3NjI3LCJpYXQiOjE2ODc3Mzk2Mjd9._wBXfotQF4-U5UDCn1kyvx8re2J1ZpQ8WolXKLnf4iVOWjPvJG5YGmSapO9MCtmlhzumoYAOIBrdY1jBUYTiDg

Request


RESPONSE

{
    "status": "success",
    "message": "Fetched All NonConnections",
    "data": [
        {
            "userName": "admin",
            "userFullName": "Admin",
            "profileImage": "logo.jpg"
        },
        {
            "userName": "asur_official",
            "userFullName": "Mukti Giver",
            "profileImage": "user4.jpeg"
        },
        {
            "userName": "axios_iiitl",
            "userFullName": "Axios Technical Society",
            "profileImage": "Axios.jpg"
        },
        {
            "userName": "demo",
            "userFullName": "Demo User",
            "profileImage": "defaultProfile.jpg"
        },
        {
            "userName": "Harshit_lcs21",
            "userFullName": "Harshit Chordiya",
            "profileImage": "harshit.jpeg"
        },
        {
            "userName": "iiitl_official",
            "userFullName": "IIIT LUCKNOW",
            "profileImage": "iiitlProfile.png"
        },
        {
            "userName": "Manish_Rawat",
            "userFullName": "Manish Rawat",
            "profileImage": "user0.jpg"
        },
        {
            "userName": "Manjeet_lcs29",
            "userFullName": "Manjeet Kumar",
            "profileImage": "man.jpg"
        },
        {
            "userName": "marvel_official",
            "userFullName": "Marvels",
            "profileImage": "user5.jpg"
        },
        {
            "userName": "Mohit_lcs32",
            "userFullName": "Mohit Raghav",
            "profileImage": "mohit.jpeg"
        },
        {
            "userName": "Sunil_lcs48",
            "userFullName": "Sunil Tirwal",
            "profileImage": "sunil.jpg"
        },
        {
            "userName": "Umesh_lcs52",
            "userFullName": "Umesh Singh Verma",
            "profileImage": "umesh.jpeg"
        },
        {
            "userName": "Yash_lcs57",
            "userFullName": "Yash Aggarwal",
            "profileImage": "yash.jpeg"
        }
    ],
    "count": 13
}
```

## 6. CONTACTUSQUERY Controller

- Create Query

```
Post : localhost:8080/contactus/create

Headers


Request Body
{
    "name":"Mohit",
    "email":"Mohit@gmail.com",
    "subject":"triaal",
    "message":"nothing to be asked "
}

RESPONSE

{
    "status": "success",
    "message": "Contact Us Query Added Successfully",
    "data": [
        {
            "queryId": 1,
            "name": "Mohit",
            "email": "Mohit@gmail.com",
            "subject": "triaal",
            "message": "nothing to be asked ",
            "answered": false
        }
    ],
    "count": 1
}
```

**OTHER CONTACT US APIS BY ONLY ADMIN**

- Set As Answered Query by QueryId

```
Post : localhost:8080/contactus/setasanswered/46
//! /setasanswered/{queryId}

Headers (admin)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4Nzc1NjYzNSwiaWF0IjoxNjg3NzM4NjM1fQ.YnaaPKIv1W_ixGAizopm7rLEnh9_fmJcdwVWkp5YlvY7_p0XgpfTyGvHegD6ASn0CSeeyHBTfg3xuVpU4oodYg


Request


RESPONSE

{
    "status": "success",
    "message": "Contact Us Query Answered Successfully",
    "data": [
        {
            "queryId": 46,
            "name": "Nimesh",
            "email": "nimesh@gmail.com",
            "subject": "Login Problem",
            "message": "I am facing problem with Login. Please Help.",
            "answered": true
        }
    ],
    "count": 1
}
```

- Get All Queries

```
Get : localhost:8080/contactus/all

RESTRICTIONS


Headers (admin)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4Nzc1NjYzNSwiaWF0IjoxNjg3NzM4NjM1fQ.YnaaPKIv1W_ixGAizopm7rLEnh9_fmJcdwVWkp5YlvY7_p0XgpfTyGvHegD6ASn0CSeeyHBTfg3xuVpU4oodYg


Request


RESPONSE

{
    "status": "success",
    "message": "Contact Us Query Fetched Successfully",
    "data": [
        {
            "queryId": 46,
            "name": "Nimesh",
            "email": "nimesh@gmail.com",
            "subject": "Login Problem",
            "message": "I am facing problem with Login. Please Help.",
            "answered": true
        },
        {
            "queryId": 47,
            "name": "Krishna Sumit",
            "email": "krishna@gmail.com",
            "subject": "Just kidding",
            "message": "Checking your ContactUs Query Page And Apis.",
            "answered": true
        },
        {
            "queryId": 48,
            "name": "Random",
            "email": "random@gmail.com",
            "subject": "Found a bug in your code",
            "message": "I have hacked your website.Pay me Rupees 6969 . Then I will tell You..Offer Valid Till Tommorrow Only.",
            "answered": false
        },
        {
            "queryId": 57,
            "name": "Mohit",
            "email": "Mohit@gmail.com",
            "subject": "triaal",
            "message": "nothing to be asked ",
            "answered": false
        }
    ],
    "count": 4
}
```

- Get Answered Queries

```
Get : localhost:8080/contactus/answered


Headers (admin)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4Nzc1NjYzNSwiaWF0IjoxNjg3NzM4NjM1fQ.YnaaPKIv1W_ixGAizopm7rLEnh9_fmJcdwVWkp5YlvY7_p0XgpfTyGvHegD6ASn0CSeeyHBTfg3xuVpU4oodYg

Request


RESPONSE

{
    "status": "success",
    "message": "Answered Contact Us Query Fetched Successfully",
    "data": [
        {
            "queryId": 46,
            "name": "Nimesh",
            "email": "nimesh@gmail.com",
            "subject": "Login Problem",
            "message": "I am facing problem with Login. Please Help.",
            "answered": true
        },
        {
            "queryId": 47,
            "name": "Krishna Sumit",
            "email": "krishna@gmail.com",
            "subject": "Just kidding",
            "message": "Checking your ContactUs Query Page And Apis.",
            "answered": true
        }
    ],
    "count": 2
}
```

- Get UnAnswered Queries

```
Get : localhost:8080/contactus/unanswered


Headers (admin)

Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY4Nzc1NjYzNSwiaWF0IjoxNjg3NzM4NjM1fQ.YnaaPKIv1W_ixGAizopm7rLEnh9_fmJcdwVWkp5YlvY7_p0XgpfTyGvHegD6ASn0CSeeyHBTfg3xuVpU4oodYg


Request


RESPONSE

{
    "status": "success",
    "message": "UnAnswered Contact Us Query Fetched Successfully",
    "data": [
        {
            "queryId": 48,
            "name": "Random",
            "email": "random@gmail.com",
            "subject": "Found a bug in your code",
            "message": "I have hacked your website.Pay me Rupees 6969 . Then I will tell You..Offer Valid Till Tommorrow Only.",
            "answered": false
        },
        {
            "queryId": 57,
            "name": "Mohit",
            "email": "Mohit@gmail.com",
            "subject": "triaal",
            "message": "nothing to be asked ",
            "answered": false
        }
    ],
    "count": 2
}
```
