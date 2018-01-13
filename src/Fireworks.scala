import processing.core._
import scala.collection.mutable.Buffer
import util.Random
import java.awt.event.KeyEvent._

class Fireworks extends PApplet {
  var fireworks = Buffer[Firework]()
  val rand = new Random()
  var particles = Buffer[Particle]()

  override def setup() = {
  }

  override def settings() = {
    width = 1000
    height = 600
  }

  override def draw() = {
    background(0, 0, 0)
    for (f <- fireworks) {
      stroke(f.r, f.g, f.b)

      f.update()
      var i = 0
      while (i < f.trace.size - 1) {
        line(f.trace(i)._1, f.trace(i)._2, f.trace(i + 1)._1, f.trace(i + 1)._2)
        i += 1
      }
      for (p <- f.particles) {
        p.update()
        var i = 0
        while (i < p.trace.size - 1) {
          line(p.trace(i)._1.toFloat, p.trace(i)._2.toFloat, p.trace(i + 1)._1.toFloat, p.trace(i + 1)._2.toFloat)
          i += 1
        }
      }

    }
  }
  def spawn() = {
    var i = 0
    while (i < 10) {
      val r = rand.nextInt(200) + 50
      val g = rand.nextInt(200) + 50
      val b = rand.nextInt(200) + 50
      fireworks += new Firework(rand.nextInt(width - 200) + 100, height, 5, r, g, b)
      i += 1
    }
  }

  override def keyPressed() = {
    
    if (keyCode == VK_UP) {
    	spawn()
    }
  }

}

object Fireworks {
  def main(args: Array[String]) {
    PApplet.main(Array[String]("Fireworks"))
  }
}