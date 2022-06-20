class NBody {
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];

		Planet Planets[] = readPlanets(fileName);
		double radius = readRadius(fileName);

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);

		double cur_time = 0;

		while(cur_time < T) {
			StdDraw.clear();
			double [] x_force = new double[Planets.length];
			double [] y_force = new double[Planets.length];

			for(int i = 0; i < Planets.length; i++) {
				x_force[i] = Planets[i].calcNetForceExertedByX(Planets);
				y_force[i] = Planets[i].calcNetForceExertedByY(Planets);
			}

			for(int i = 0; i < Planets.length; i++) {
				Planets[i].update(dt, x_force[i], y_force[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg", 2*radius, 2*radius);

			for(int i = 0; i < Planets.length; i++) {
				Planets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

			cur_time += dt;
		}
		StdOut.printf("%d\n", Planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < Planets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  Planets[i].xxPos, Planets[i].yyPos, Planets[i].xxVel,
                  Planets[i].yyVel, Planets[i].mass, Planets[i].imgFileName);   
}

	}


	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String fileName) {
		In in = new In(fileName);
		int num_Planets = in.readInt();
		in.readDouble();
		Planet[] res = new Planet[num_Planets];
		for(int i = 0; i < num_Planets; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			Planet a = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
			res[i] = a;
		}
		return res;
	}
}