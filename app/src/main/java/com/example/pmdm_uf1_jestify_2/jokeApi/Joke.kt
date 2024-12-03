package com.example.pmdm_uf1_jestify_2.jokeApi

class Joke {
    private var error = false
    private var category: String? = null
    private var type: String? = null
    private var joke: String? = null
    private var setUp: String? = null
    private var delivery: String? = null
    private var flags: Map<Flags?, Boolean>? = null
    private var id = 0
    private var safe = false
    private var lang: String? = null

    fun setError(error: Boolean) {
        this.error = error
    }

    fun setCategory(category: String?) {
        this.category = category
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun setJoke(joke: String?) {
        this.joke = joke
    }

    fun setSetUp(setUp: String?) {
        this.setUp = setUp
    }

    fun setDelivery(delivery: String?) {
        this.delivery = delivery
    }

    fun setFlags(flags: Map<Flags?, Boolean>?) {
        this.flags = flags
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setSafe(safe: Boolean) {
        this.safe = safe
    }

    fun setLang(lang: String?) {
        this.lang = lang
    }
}