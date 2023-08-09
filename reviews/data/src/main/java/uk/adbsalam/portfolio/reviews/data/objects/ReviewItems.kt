package uk.adbsalam.portfolio.reviews.data.objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewItems(
    @Json(name = "review") val reviews: List<Review>
) {
    @JsonClass(generateAdapter = true)
    data class Review(
        @Json(name = "name") val name: String,
        @Json(name = "designation") val designation: String,
        @Json(name = "relation") val relation: String,
        @Json(name = "review") val review: String,
        @Json(name = "relationDetails") val relationDetails: String,
    )

    companion object {
        fun createMock() = ReviewItems(
            listOf(
                Review(
                    name = "Daniel Batisdas Ramirez",
                    designation = "Tech Lead at NBrown Group",
                    relation = "Line Manager/Tech Lead at NBrown Group",
                    relationDetails = "I worked with Daniel throughout my time at NBrown Group. Most of the time I spent with Daniel was while attending his IOS Knowledge sharing sessions. Along with architectural discussions",
                    review = "Salam is an exceptional talent in the world of Android development, for now, and pretty sure he will make big impact with other technologies. He has made a significant impact during the time we worked together and I’m hoping our paths will cross and enjoy working on a project.\n" +
                            "\n" +
                            "He has continuously demonstrated the qualities of a proactive, ingenious, and creative professional, capable of delivering outstanding results in a fast-paced and demanding environment.",
                ),
                Review(
                    name = "Ian J Hayward",
                    designation = "Software Engineering Team Lead at BBC",
                    relation = "Line Manager/Tech Lead at NBrown",
                    relationDetails = "Ian J Hayward was my Tech Lead. I loved leadership of Ian J Hayward, His attitude towards learning and upskilling team is amazing. Majority of time we spend together was during architectural discussions for NBrown Group Projects",
                    review = "Salam is a fantastic engineer. He is an excellent communicator, self-motivated, great at solving tricky tasks, and working with multiple stakeholders. Working with him was great, hopefully I will run into him again in the future.",
                ),

                Review(
                    name = "Nathan Lawlor",
                    designation = "IOS Developer",
                    relation = "Work together in different teams",
                    relationDetails = "Nathan Lawlor and I were collaborating on Data Reporting for Apps, I was Android representative and Nathan was IOS Representative, we worked closely to find best solutions for both Android and IOS. We also were part of same team for NBrow Group Hackathon 2023 and won prizes for Most Innovative Project and Best pitch of the hackathon",
                    review = "Salam is a well established developer that considers the latest technologies and potential innovation. His aptitude for learning is one of his best qualities, as well as being passionate about his projects. I have worked with Salam on several projects; He is always welcoming to discuss any thoughts, concerns, or strategies to move forward. Moreover, he is a cheerful character, and would be a great addition to any team.",
                ),
                Review(
                    name = "Samuel I Olugbemi",
                    designation = "Senior Engineer at Cutover",
                    relation = "Senior to me at Payzone Bill Payments",
                    relationDetails = "Sameul I Olugbemi was the first mentor ever who gave me lessons on clean code, from there on I followed his advice and always focus on clean code. We worked together on a very complex project in Payzone known as Quantum, This is an android based Application to topup Gas Cards using Java and C++",
                    review = "Muhammad Abdulsalam's work ethics and passion to provide only the very best solution makes him outstanding! We both work in the same team and he is an approachable and fantastic software engineer with a good sense of humour.",
                ),
                Review(
                    name = "Alex Vladut",
                    designation = "Senior Contractor AWS",
                    relation = "Senior to me at Payzone Bill Payments",
                    relationDetails = "Alex Vladut was Senior Web Developer at payzone at time, we worked together on regular basis as we were working on a web app where Alex had lead in Angular/Web side of App and I was taking lead at Android/JAVA side of project",
                    review = "Salam is such a delightful person to have around. He is innovative, organized as well as helpful and is always open to ideas and suggestions. I highly recommend working with him to get the best out of everything.",
                ),
                Review(
                    name = "Peter Carey",
                    designation = "Front End Software Developer",
                    relation = "Worked together at Payzone Bill Payments",
                    relationDetails = "Peter Cary was Senior Web Developer at payzone at time, we worked together on regular basis as we were working on a web app where Peter Cary was working on Angular/Web side of App and I was working on Android/JAVA side of project",
                    review = "Salam worked with me on the same team at Payzone. He is a great to work with and is very passionate about Programming. Each day he was at Payzone he was enthusiastic about the Projects he was working on. He was always willing to take on any additional programming tasks and was always available to assist. Salem is very keen to learn new Programming languages and Technologies and pass information acquired on to his colleagues.",
                )
            )
        )
    }
}
