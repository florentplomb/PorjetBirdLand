# klikEditor
This addon propose some interesting tools to create an editor.

#### If your layer is a graphic one, you can use this following methods:

### graphicLayer.newPolygon()

```javascript
// Add a polygon to your graphic Layer
var polygon = yourGraphicLayer.newPolygon();
```
it return a polygon object. 

### polygon.addPoint(pointPos)
#### PointPos is the position of that you want to give to your point.
```javascript
var pointPos = {
  'x': Number, // in px 
  'y': Number // in px
}
```
### polygon.update(points)
A polygon is build with points and a path that are go trough all this points.
To update your polygon (your path in reality), call this method sending all your points in the px format.

### polygon.getPoints()
Returns all the points objects of the specified polygon, in the px format. 

### graphicLayer.polygonView()
This method allows you to see in live in your editor which elements are already drawn.
When you draw a polygon, he will directly be in colour.

### polygon.start()
Start editing

### polygon.stop()
Stop editing.
This method will close your path and your polygon

