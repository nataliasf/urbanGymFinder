package com.example.urbangymfinder

import java.io.Serializable

public open class Event : Serializable {
    var name: String? = null
    var date: String? = null
    var time: String? = null
    var location: String? = null
    var description: String? = null

    constructor() {}

    constructor( name: String, date: String, time: String, location: String, description: String ){
        this.name = name
        this.date = date
        this.time = time
        this.location = location
        this.description = description
    }

}
