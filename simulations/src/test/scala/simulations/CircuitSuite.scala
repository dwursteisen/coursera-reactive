package simulations

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CircuitSuite extends CircuitSimulator with FunSuite {
  val InverterDelay = 1
  val AndGateDelay = 3
  val OrGateDelay = 5

  test("andGate example") {
    val in1, in2, out = new Wire
    andGate(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run

    assert(out.getSignal === false, "and 1")

    in1.setSignal(true)
    run

    assert(out.getSignal === false, "and 2")

    in2.setSignal(true)
    run

    assert(out.getSignal === true, "and 3")
  }

  test("orGate") {
    val in1, in2, out = new Wire
    orGate(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run
    assert(out.getSignal === false, "and 1")

    in1.setSignal(true)
    in2.setSignal(false)
    run
    assert(out.getSignal === true, "and 2")

    in1.setSignal(false)
    in2.setSignal(true)
    run
    assert(out.getSignal === true, "and 4")

    in1.setSignal(true)
    in2.setSignal(true)
    run
    assert(out.getSignal === true, "and 5")

  }

  test("orGate2") {
    val in1, in2, out = new Wire
    orGate2(in1, in2, out)
    in1.setSignal(false)
    in2.setSignal(false)
    run
    assert(out.getSignal === false, "and 1")

    in1.setSignal(true)
    in2.setSignal(false)
    run
    assert(out.getSignal === true, "and 2")

    in1.setSignal(false)
    in2.setSignal(true)
    run
    assert(out.getSignal === true, "and 4")

    in1.setSignal(true)
    in2.setSignal(true)
    run
    assert(out.getSignal === true, "and 5")

  }


  //
  // to complete with tests for orGate, demux, ...
  //
  test("demux") {
    val in, out = new Wire
    demux(in, List(), List(out))
    in.setSignal(true)
    run

    assert(out.getSignal === true, "and 1")

    in.setSignal(false)
    run
    assert(out.getSignal === false, "and 2")

  }
  test("demux2") {
    val in, out1, out2, c = new Wire
    demux(in, List(c), List(out1, out2))
    in.setSignal(true)
    c.setSignal(false)
    run

    assert(out1.getSignal === true, "and 1")
    assert(out2.getSignal === false, "and 1bis")

    in.setSignal(false)
    run
    assert(out1.getSignal === false, "and 2")
    assert(out1.getSignal === false, "and 2")


    in.setSignal(true)
    c.setSignal(true)
    run

    assert(out1.getSignal === false, "and 1")
    assert(out2.getSignal === true, "and 1bis")

    in.setSignal(false)
    run
    assert(out1.getSignal === false, "and 2")
    assert(out1.getSignal === false, "and 2")

  }

}
