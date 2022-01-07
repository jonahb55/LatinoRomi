// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  private final TransmisionRomi transmision = new TransmisionRomi();
  private final GiroscopioRomi giroscopio = new GiroscopioRomi();

  @Override
  public void robotInit() {
  }

  @Override
  public void robotPeriodic() {
    String entradas = "";
    entradas += DriverStation.getInstance().isEnabled() ? "encendido," : "apagado,";
    entradas += Double.toString(transmision.conseguirDistanciaIzquierdoPulgadas()) + ",";
    entradas += Double.toString(transmision.conseguirDistanciaDerechoPulgadas()) + ",";
    entradas += Double.toString(giroscopio.conseguirAnguloZ());
    try {
      FileWriter archivoDeEntradas = new FileWriter("io/entradas.txt");
      archivoDeEntradas.write(entradas);
      archivoDeEntradas.close();
    } catch (IOException e) {
      DriverStation.reportError("No puedo escribir las entradas.", false);
    }
  }

  @Override
  public void autonomousInit() {
    giroscopio.reinciar();
  }

  @Override
  public void autonomousPeriodic() {
    try {
      String salidas = new String(Files.readAllBytes(Paths.get("io/salidas.txt")), StandardCharsets.UTF_8);
      if (salidas != "") {
        String[] partes = salidas.split(",");
        try {
          transmision.manejar(Double.parseDouble(partes[0]), Double.parseDouble(partes[1]));
        } catch (NumberFormatException e) {
        }
      }
    } catch (IOException e) {
      DriverStation.reportError("No puedo leer las salidas.", false);
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void disabledInit() {
    transmision.manejar(0.0, 0.0);
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
  }
}
