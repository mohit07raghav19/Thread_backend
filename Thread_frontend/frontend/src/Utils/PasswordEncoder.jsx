import bcrypt from "bcryptjs";

export const PasswordEncoder = (pass) => {
    var hashedpass = bcrypt.hashSync(pass,10);
  
    console.log(hashedpass);
    return hashedpass ;
  };