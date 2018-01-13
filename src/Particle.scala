import collection.mutable.Queue
import scala.math._
import processing.core._

class Particle(var x_0: Float, var y_0: Float, var partX: Float, var partY: Float, val speed: Int, r: Int, g: Int, b: Int) {
  var trace = Queue[(Float, Float)]()
  var location = new PVector(x_0, y_0) //vektori origosta spawnauspisteeseen, pysyy vakiona
  var partLocation = new PVector(partX, partY)
  var velocity = partLocation.copy().sub(location)
	var gravity = new PVector(0, 0.2f)
  var flag = false
  
  def update() = {
    if(!flag){
    	velocity.sub(gravity)
    	velocity.normalize()     // "normalisoidaan", eli luodaan yksikkövektori
    	velocity.mult(speed)     // kasvatetaan nopeusvektoria niin paljon ku tarvii
    	location.sub(velocity)
    	trace.enqueue((location.x, location.y))	    
      flag = trace.size > 30
    }else if(!trace.isEmpty)
      trace.dequeue()
  }
  
}