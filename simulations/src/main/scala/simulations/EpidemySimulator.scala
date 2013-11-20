package simulations

import math.random

class EpidemySimulator extends Simulator {

  def randomBelow(i: Int) = (random * i).toInt

  protected[simulations] object SimConfig {
    val population: Int = 300
    val roomRows: Int = 8
    val roomColumns: Int = 8

    // to complete: additional parameters of simulation
    val prevalenceRate: Float = 0.01f

    def numberOfInfected: Int = Math.round(population * prevalenceRate)
  }

  import SimConfig._

  val persons: List[Person] = buildPersons()

  def buildPersons(): List[Person] = {
    val peoples: Seq[Person] = for {
      id <- 0 to population
    } yield {
      val p = new Person(id)
      p.infected = id < numberOfInfected
      p
    }
    peoples.toList
  }

  class Person (val id: Int) {
    var infected = false
    var sick = false
    var immune = false
    var dead = false

    // demonstrates random number generation
    var row: Int = randomBelow(roomRows)
    var col: Int = randomBelow(roomColumns)

    //
    // to complete with simulation logic
    //
  }
}
