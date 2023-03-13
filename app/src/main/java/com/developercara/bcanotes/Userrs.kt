package com.developercara.bcanotes

 class Userrs {
    var name: String? = null
    var email: String? = null
    var photoUrl: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User::class.java)
    }

    constructor(name: String?, email: String?, photoUrl: String?) {
        this.name = name
        this.email = email
        this.photoUrl = photoUrl
    }
}



