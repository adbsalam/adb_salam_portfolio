package uk.adbsalam.portfolio.communication

data class NearbyUsers(
    val users: ArrayList<User> = arrayListOf()
) {
    data class User(
        val id: String,
        val name: String,
    )

    companion object {
        fun mockUsers() = NearbyUsers(
            users = arrayListOf(
                User(
                    id = "01",
                    name = "testUser"
                ),
                User(
                    id = "01",
                    name = "testUser"
                ),
                User(
                    id = "01",
                    name = "testUser"
                ),
            )
        )
    }
}
