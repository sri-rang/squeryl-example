package net.srirangan.opensource.squerylexample.schema

import org.squeryl._
import org.squeryl.PrimitiveTypeMode._
import net.srirangan.opensource.squerylexample.entities.User

object Schema extends Schema {

  val users = table[User]

  on(users)(user => declare(
      user.id is (autoIncremented),
      user.email is (unique)
    ))

}
