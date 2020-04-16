public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p){
        double dx = this.xxPos-p.xxPos;
        dx = dx*dx;
        double dy = this.yyPos - p.yyPos;
        dy = dy * dy;
        return Math.sqrt(dx + dy);
    }
    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        if(r == 0){
            return 0;
        }
        return G*this.mass*p.mass/(r*r);
    }
    public double calcForceExertedByX(Planet p){
        double r = calcDistance(p);
        if(r == 0){
            return 0;
        }
        return calcForceExertedBy(p)*(p.xxPos-this.xxPos)/r;
    }
    public double calcForceExertedByY(Planet p){
        double r = calcDistance(p);
        if(r == 0){
            return 0;
        }
        return calcForceExertedBy(p)*(p.yyPos-this.yyPos)/r;
    }
    public double calcNetForceExertedByX(Planet[] allP){
        double f = 0;
        for(int i=0; i<allP.length; i++){
            f+=calcForceExertedByX(allP[i]);
        }
        return f;
    }
    public double calcNetForceExertedByY(Planet[] allP){
        double f = 0;
        //below can also be written as: for (String element : array) {
        for(int i=0; i<allP.length; i++){
            f+=calcForceExertedByY(allP[i]);
        }
        return f;
    }
    /** update function */
    public void update(double dt, double fX, double fY){
        this.xxVel+=fX/this.mass*dt;
        this.xxPos+=this.xxVel*dt;
        this.yyVel+=fY/this.mass*dt;
        this.yyPos+=this.yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+ this.imgFileName);
    }

}
