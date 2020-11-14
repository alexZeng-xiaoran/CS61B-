class Planet{
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet() {
    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }
    public double calcDistance(Planet p){
        double dx=p.xxPos-this.xxPos;
        double dy=p.yyPos-this.yyPos;
        return Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
    }

    public double calcForceExertedBy(Planet p){
        /*java中的const关键字不表示常量*/
        double G=6.67e-11;
        double distance=this.calcDistance(p);
        double force=G*this.mass*p.mass/(distance*distance);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double dx=p.xxPos-this.xxPos;
        double distance=this.calcDistance(p);
        double force=this.calcForceExertedBy(p);
        double forceX=dx*force/distance;
        return forceX;
    }

    public double calcForceExertedByY(Planet p){
        double dy=p.yyPos-this.yyPos;
        double distance=this.calcDistance(p);
        double force=this.calcForceExertedBy(p);
        double forceY=dy*force/distance;
        return forceY;
    }
    public boolean equal(Planet p){
        if(this.xxPos==p.xxPos &&  this.yyPos==p.yyPos){
            return true;
        }else{
            return false;
        }
    }

    public double calcNetForceExertedByX(Planet[] allPlanet){
        double netForceX=0;

        for(Planet p : allPlanet){
            if(this.equal(p)==false){
                netForceX+=this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanet){
        double netForceY=0;
        for(Planet p : allPlanet){
            if(this.equal(p)==false){
                netForceY+=this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt,double Fx,double Fy){
        double accelerationX=Fx/this.mass;
        double accelerationY=Fy/this.mass;
        this.xxVel=this.xxVel+dt*accelerationX;
        this.yyVel=this.yyVel+dt*accelerationY;
        this.xxPos+=this.xxVel*dt;
        this.yyPos+=this.yyVel*dt;

    }
    public void draw(){
        double x=this.xxPos;
        double y=this.yyPos;
        System.out.println(this.imgFileName);
        StdDraw.picture(x,y,"images/"+this.imgFileName);
    }
}