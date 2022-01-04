// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.hal.SimDevice;
import edu.wpi.first.hal.SimDevice.Direction;
import edu.wpi.first.hal.SimDouble;

public class GiroscopioRomi {
  private SimDouble velocidadSimX;
  private SimDouble velocidadSimY;
  private SimDouble velocidadSimZ;
  private SimDouble anguloSimX;
  private SimDouble anguloSimY;
  private SimDouble anguloSimZ;

  private double anguloCompensacionSimX;
  private double anguloCompensacionSimY;
  private double anguloCompensacionSimZ;

  /** Create a new RomiGyro. */
  public GiroscopioRomi() {
    SimDevice GiroscopioSim = SimDevice.create("Gyro:RomiGyro");
    if (GiroscopioSim != null) {
      GiroscopioSim.createBoolean("init", Direction.kOutput, true);
      velocidadSimX = GiroscopioSim.createDouble("rate_x", Direction.kInput, 0.0);
      velocidadSimY = GiroscopioSim.createDouble("rate_y", Direction.kInput, 0.0);
      velocidadSimZ = GiroscopioSim.createDouble("rate_z", Direction.kInput, 0.0);

      anguloSimX = GiroscopioSim.createDouble("angle_x", Direction.kInput, 0.0);
      anguloSimY = GiroscopioSim.createDouble("angle_y", Direction.kInput, 0.0);
      anguloSimZ = GiroscopioSim.createDouble("angle_z", Direction.kInput, 0.0);
    }
  }

  public double conseguirVelocidadX() {
    if (velocidadSimX != null) {
      return velocidadSimX.get();
    }

    return 0.0;
  }

  public double conseguirVelocidadY() {
    if (velocidadSimY != null) {
      return velocidadSimY.get();
    }

    return 0.0;
  }

  public double conseguirVelocidadZ() {
    if (velocidadSimZ != null) {
      return velocidadSimZ.get();
    }

    return 0.0;
  }

  public double conseguirAnguloX() {
    if (anguloSimX != null) {
      return anguloSimX.get() - anguloCompensacionSimX;
    }

    return 0.0;
  }

  public double conseguirAnguloY() {
    if (anguloSimY != null) {
      return anguloSimY.get() - anguloCompensacionSimY;
    }

    return 0.0;
  }

  public double conseguirAnguloZ() {
    if (anguloSimZ != null) {
      return anguloSimZ.get() - anguloCompensacionSimZ;
    }

    return 0.0;
  }

  public void reinciar() {
    if (anguloSimX != null) {
      anguloCompensacionSimX = anguloSimX.get();
      anguloCompensacionSimY = anguloSimY.get();
      anguloCompensacionSimZ = anguloSimZ.get();
    }
  }
}
