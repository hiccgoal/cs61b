class Planet {
 double xxPos;
 double yyPos;
 double xxVel;
 double yyVel;
 double mass;
 String imgFileName;
 static final double G = 6.67e-11;

 public Planet(double xP, double yP, double xV,
               double yV, double m, String img) {
  xxPos = xP;
  yyPos = yP;
  xxVel = xV;
  yyVel = yV;
  mass = m;
  imgFileName = img;
 }

 public Planet(Planet p) {
  xxPos = p.xxPos;
  yyPos = p.yyPos;
  xxVel = p.xxVel;
  yyVel = p.yyVel;
  mass = p.mass;
  imgFileName = p.imgFileName;
 }

 public double calcDistance(Planet a) {
  double distance_x = (this.xxPos-a.xxPos)*(this.xxPos-a.xxPos);
  double distance_y = (this.yyPos-a.yyPos)*(this.yyPos-a.yyPos);
  return Math.sqrt(distance_x + distance_y);
 }

 public double calcForceExertedBy(Planet a) {
  return G * this.mass * a.mass / (this.calcDistance(a)*this.calcDistance(a));
 }

 public double calcForceExertedByX(Planet a) {
  double dx = a.xxPos - this.xxPos;
  return (dx / calcDistance(a) ) * calcForceExertedBy(a); 
 }

 public double calcForceExertedByY(Planet a) {
  double dy = a.yyPos - this.yyPos;
  return (dy / calcDistance(a) ) * calcForceExertedBy(a);
 }

 public double calcNetForceExertedByX(Planet[] allPlanets) {
  double sum = 0;
  for (int i = 0; i < allPlanets.length; i++) {
   if(this.xxPos == allPlanets[i].xxPos && this.yyPos == allPlanets[i].yyPos) {
    continue;
   }
   sum += this.calcForceExertedByX(allPlanets[i]);
  }
  return sum;
 }

 public double calcNetForceExertedByY(Planet[] allPlanets) {
  double sum = 0;
  for (int i = 0; i < allPlanets.length; i++) {
   if(this.xxPos == allPlanets[i].xxPos && this.yyPos == allPlanets[i].yyPos) {
    continue;
   }
   sum += this.calcForceExertedByY(allPlanets[i]);
  }
  return sum;
 }

 public void update(double dt,double fX,double fY) {
  double a_x = fX / this.mass;
  double a_y = fY / this.mass;
  this.xxVel += a_x * dt;
  this.yyVel += a_y * dt;
  this.xxPos += this.xxVel * dt;
  this.yyPos += this.yyVel * dt;
 }

 public void draw() {
  String filePath = "images/" + this.imgFileName.toString();
  StdDraw.picture(this.xxPos, this.yyPos, filePath);
}

}