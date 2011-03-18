package net.srirangan.opensource.squerylexample.entities

class User(var email:String, var password:String) extends BaseEntity {

  // Zero argument constructor required
  // Squeryl Roadmap says 0.9.5 will not need them :-)
  def this() = this("", "")
  
}
