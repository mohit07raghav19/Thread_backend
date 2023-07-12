package com.example.thread.TeamAboutUs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.thread.TeamAboutUs.model.AboutUsTeam;
import com.example.thread.TeamAboutUs.repo.AboutUsTeamRepo;

@Service
public class AboutUsTeamServiceImpl implements AboutUsTeamService {

        @Autowired
        private AboutUsTeamRepo aboutRepo;

        @Override
        public void initTeam() {
                if (aboutRepo.findAll().size() == 0) {
                        AboutUsTeam mem1 = new AboutUsTeam("HarshitP.jpg",
                                        "Harshit Chordiya",
                                        "Student at IIIT Lucknow (LCS2022021).A dynamic and experienced professional with a passion for leading and motivating team towards success.",
                                        "Team Leader",

                                        "harshitchordiya03@gmail.com",
                                        "https://www.linkedin.com/in/harshit-chordiya-b5b786259",
                                        "https://twitter.com/", "https://github.com/Harshit-Chordiya");

                        AboutUsTeam mem2 = new AboutUsTeam("yash.jpeg",
                                        "Yash Agarwal",
                                        "Student at IIIT Lucknow (LCS2022057). He is passionate about work and always goes extra mile to ensure that projects are completed on time and to the highest standard.",
                                        "Team Member",
                                        "yash74260agr@gmail.com", "https://www.linkedin.com/in/",
                                        "https://twitter.com/", "https://github.com/Yash7426");
                        AboutUsTeam mem3 = new AboutUsTeam("mo.jpeg",
                                        "Mohit Raghav",
                                        "Student at IIIT Lucknow (LCS2022032).His attention to detail and analytical skills have been instrumental in guiding team through projects.",
                                        "Team Member",

                                        "mohit07raghav19@gmail.com",
                                        "https://www.linkedin.com/in/mohit-raghav-47883124a/",
                                        "https://twitter.com/Mohit07Raghav19", "https://github.com/mohit07raghav19");
                        AboutUsTeam mem4 = new AboutUsTeam("umesh.jpeg",
                                        "Umesh Singh Verma",
                                        "Student at IIIT Lucknow (LCS2022052).One of our most dedicated team members.He brings a wealth of knowledge and expertise to our team. ",
                                        "Team Member",

                                        "lcs2022052@iiitl.ac.in",
                                        "https://www.linkedin.com/in/umesh-singh-verma-425a4b221/",
                                        "https://twitter.com/", "https://github.com/umeshSinghVerma");
                        AboutUsTeam mem5 = new AboutUsTeam("man.jpg",
                                        "Manjeet Kumar",
                                        "Student at IIIT Lucknow (LCS2022029).His strong work ethic and positive attitude have made him an invaluable member of our team. ",
                                        "Team Member",

                                        "manjeetkumar36942336@gmail.com",
                                        "https://www.linkedin.com/in/manjeet-kumar-10a78b259/",
                                        "https://twitter.com/MK23082002", "https://github.com/manjeetkumar29");
                        AboutUsTeam mem6 = new AboutUsTeam("sunil.jpg",
                                        "Sunil Tirwal",
                                        "Student at IIIT Lucknow (LCS2022048).With his passion for our company, attention to detail, and excellent communication skills, it makes him an asset to our team. ",
                                        "Team Member",

                                        "lcs2022048@iiitl.ac.in", "https://www.linkedin.com/in/sunil-tirwal-75365825b/",
                                        "https://twitter.com/", "https://github.com/suniltirwal1012");
                        aboutRepo.save(mem1);
                        aboutRepo.save(mem2);
                        aboutRepo.save(mem3);
                        aboutRepo.save(mem4);
                        aboutRepo.save(mem5);
                        aboutRepo.save(mem6);

                }
        }

        @Override
        public List<AboutUsTeam> getTeam() {
                return this.aboutRepo.findAll();
        }

        @Override
        public List<AboutUsTeam> addMember(AboutUsTeam member) {
                this.aboutRepo.save(member);
                return this.aboutRepo.findAll();
        }
}