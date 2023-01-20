package com.exemple.TheMovieDB.model.response.base

abstract class BaseType{
    abstract fun getType() :Int

    companion object{
        val TYPE_Popular = 0
        val TYPE_TopRated = 1
        val TYPE_Upcoming = 2
        val TYPE_NowPlaying = 3

        val TYPE_Similar = 4
        val TYPE_Actor = 5
        val TYPE_Trailer = 6
    }
}