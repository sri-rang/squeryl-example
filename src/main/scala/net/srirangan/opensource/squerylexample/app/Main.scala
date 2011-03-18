package net.srirangan.opensource.squerylexample.app

import org.squeryl.Session
import org.squeryl.SessionFactory
import org.squeryl.PrimitiveTypeMode._
import org.squeryl.adapters.MySQLAdapter
import net.srirangan.opensource.squerylexample.entities.User
import net.srirangan.opensource.squerylexample.schema.Schema

object Main {

  val databaseUsername = "squeryl-example"
  val databasePassword = "squeryl-example"
  val databaseConnection = "jdbc:mysql://localhost:3306/squeryl-example"

  def main(args:Array[String]):Unit = {

    startDatabaseSession()

    transaction {
      Schema.create
      println("Created the schema")
    }

    transaction {
      val user1:User = new User("user1@domain.com", "oldPassword")
      Schema.users.insert(user1)
      println("Inserted user1")
      
      user1.password = "newPassword"
      Schema.users.update(user1)
      println("Updated user1")
    }

    transaction {
      // Duplicate email, fail!
//      val duplicateUser1:User = new User("user1@domain.com", "oldPassword")
//      Schema.users.insert(duplicateUser1)
//      println("Duplicate user can't be inserted")
    }

    transaction {
      val user2:User = new User("user2@domain.com", "password")
      Schema.users.insert(user2)
      println("Inserted user2")
    }

    transaction {
      val queriedUser:User = Schema.users.where(user => user.id === 2L).single
      println(queriedUser.id + " -- " + queriedUser.email)
    }
  }

  def startDatabaseSession():Unit = {
    Class.forName("com.mysql.jdbc.Driver")
      SessionFactory.concreteFactory = Some(() => Session.create(
          java.sql.DriverManager.getConnection(databaseConnection, databaseUsername, databasePassword),
          new MySQLAdapter)
        )
  }

}
