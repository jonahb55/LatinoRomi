// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class TransmisionRomi {
  private static final double cuentaPorRevolucion = 1440.0;
  private static final double diametroDeRuedaPulgadas = 2.75591; // 70 mm

  private final Spark motorIzquierdo = new Spark(0);
  private final Spark motorDerecho = new Spark(1);

  private final Encoder codificadorIzquierdo = new Encoder(4, 5);
  private final Encoder codificadorDerecho = new Encoder(6, 7);

  /** Crear una TransmisionRomi nueva. */
  public TransmisionRomi() {
    codificadorIzquierdo.setDistancePerPulse((Math.PI * diametroDeRuedaPulgadas) / cuentaPorRevolucion);
    codificadorDerecho.setDistancePerPulse((Math.PI * diametroDeRuedaPulgadas) / cuentaPorRevolucion);
    reinciarCodificadores();
  }

  public void manejar(double porcentajeIzquierdo, double porcentajeDerecho) {
    motorIzquierdo.set(porcentajeIzquierdo);
    motorDerecho.set(porcentajeDerecho * -1);
  }

  public void reinciarCodificadores() {
    codificadorIzquierdo.reset();
    codificadorDerecho.reset();
  }

  public double conseguirDistanciaIzquierdoPulgadas() {
    return codificadorIzquierdo.getDistance();
  }

  public double conseguirDistanciaDerechoPulgadas() {
    return codificadorDerecho.getDistance();
  }
}
