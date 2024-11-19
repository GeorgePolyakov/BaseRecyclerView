package com.base.baserecyclerview.model


import com.github.javafaker.Faker;
import java.util.Collections

typealias UsersListener = (user: List<User>) -> Unit

class UserService {

    private val users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
        val generateUsers = (1..100).map{
            User (
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }
    }

    fun getUser() : List<User> {
        return users
    }

    fun deleteUser(user : User){
        val indexToDelete = users.indexOfFirst {
            it.id == user.id
        } // return first finded element or -1

        if(indexToDelete!=-1){
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy : Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if(oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if(newIndex < 0 || newIndex >= users.size) return // if we are in the beggining of list
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

    fun addListener(listener : UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { listener ->
            listener.invoke(users)
        }
    }

    companion object {
        private val IMAGES = mutableListOf(
            "https://images.unsplash.com/photo-1600267185393-e158a98703de?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1579710039144-85d6bdffddc9?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1620252655460-080dbec533ca?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1613679074971-91fc27180661?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1485795959911-ea5ebf41b6ae?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1545996124-0501ebae84d0?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/flagged/photo-1568225061049-70fb3006b5be?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1567186937675-a5131c88a9ea?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf",
            "https://images.unsplash.com/photo-1546456073-92b9f0a8d413?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=Mnwxf"
        )
    }
}