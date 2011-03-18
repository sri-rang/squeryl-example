package net.srirangan.opensource.squerylexample.entities

import java.sql.Timestamp
import org.squeryl.KeyedEntity

class BaseEntity extends KeyedEntity[Long] {

  val id:Long = 0
  var lastModified = new Timestamp(System.currentTimeMillis)

}
