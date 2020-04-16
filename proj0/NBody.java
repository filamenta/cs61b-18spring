public class NBody {
    /**
     *  Read radius from file
     *  @param String Filename
     *  @return a double corresponding to the radius of the universe in that file
     */
    public static double readRadius(String Filename){
        In in = new In(Filename);
        int num = in.readInt();
        return in.readDouble();
    }
    /**
     *  Read Planet from file
     *  @param String file name
     *  @return an array of Planets corresponding to the planets in the file
     */
    public static Planet[] readPlanets(String Filename){
        In in = new In(Filename);
        int num = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[num];
        for(int i = 0; i<num; i+=1) {
            p[i] = new Planet(in.readDouble(), in.readDouble(),
                in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return p;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        /* Draw */
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();

        for(double t=0; t<T; t+=dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i=0; i<planets.length; i++){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for(int i=0; i<planets.length; i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet p: planets){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        //after reaching T
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
