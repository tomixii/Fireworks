import util.Random
import collection.mutable.Buffer
import processing.core._
import collection.mutable.Queue

class Firework(x_0: Int, y_0: Int, speed: Int, var r: Int, var g: Int, var b: Int) {
  val rand = new Random()
  val bool = rand.nextBoolean()
  var trace = Queue[(Float, Float)]()
  var location = new PVector(x_0, y_0) //vektori origosta spawnauspisteeseen, pysyy vakiona
  var gravity = new PVector(0, 0.1f)
  var velocity = new PVector(if(bool)rand.nextFloat() + x_0 else -rand.nextFloat() + x_0, 20 + y_0).sub(location)
  var particles = Buffer[Particle]()
  var maximumEx = 20
  var radius = 200
  var exploded = false
  val explosionHeight = rand.nextInt(150) + 100
  var flag = false

  def update() = {
    if (location.y < explosionHeight) {
    	if(!exploded) explode()      
    } else {
    	velocity.sub(gravity)
    	velocity.normalize() // "normalisoidaan", eli luodaan yksikkövektori
    	velocity.mult(speed) // kasvatetaan nopeusvektoria niin paljon ku tarvii
    	location.sub(velocity)
    	trace.enqueue((location.x, location.y))
    }
    if(!flag) 
      flag = trace.size > 50
    else if(!trace.isEmpty)
      trace.dequeue()

  }

  def explode() = {
    exploded = true
    var ex = rand.nextInt(maximumEx) + 5
    var i = 0
    while (i < ex) {
      var exX = (Math.cos((i * (360 / ex) + radius).toRadians)).toFloat + location.x
      var exY = (Math.sin((i * (360 / ex) + radius).toRadians)).toFloat + location.y
      particles += new Particle(location.x, location.y, exX, exY, 3, r, g, b)
      i += 1
      //    	particles += new Particle(exX, exY, 5, r, g, b)
    }
  }
}  