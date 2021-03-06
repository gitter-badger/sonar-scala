/*
 * Sonar Scoverage Plugin
 * Copyright (C) 2013 Rado Buransky
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package com.buransky.plugins.scoverage.measure

import java.{io, lang, util}

import org.sonar.api.measures.Metric.ValueType
import org.sonar.api.measures.{CoreMetrics, Metric, Metrics}

import scala.collection.JavaConverters._
import scala.collection.mutable.ListBuffer

/**
 * Statement coverage metric definition.
 *
 * @author Rado Buransky
 */
class ScalaMetrics extends Metrics {
  override def getMetrics: util.List[Metric[_ <: io.Serializable]] =
    ListBuffer[Metric[_ <: java.io.Serializable]](
      ScalaMetrics.statementCoverage,
      ScalaMetrics.coveredStatements,
      ScalaMetrics.totalStatements
    ).asJava
}

object ScalaMetrics {
  private val STATEMENT_COVERAGE_KEY = "scoverage"
  private val COVERED_STATEMENTS_KEY = "covered_statements"
  private val TOTAL_STATEMENTS_KEY = "total_statements"

  lazy val statementCoverage: Metric[lang.Double] =
    new Metric.Builder(STATEMENT_COVERAGE_KEY, "Statement coverage", ValueType.PERCENT)
      .setDescription("Statement coverage by tests")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(true)
      .setDomain(CoreMetrics.DOMAIN_TESTS)
      .setWorstValue(0.0)
      .setBestValue(100.0)
      .create[java.lang.Double]()

  lazy val coveredStatements: Metric[Integer] =
    new Metric.Builder(COVERED_STATEMENTS_KEY, "Covered statements", Metric.ValueType.INT)
      .setDescription("Number of statements covered by tests")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_SIZE)
      .create[java.lang.Integer]()

  lazy val totalStatements: Metric[Integer] =
    new Metric.Builder(TOTAL_STATEMENTS_KEY, "Total statements", Metric.ValueType.INT)
      .setDescription("Number of all statements covered by tests and uncovered")
      .setDirection(Metric.DIRECTION_BETTER)
      .setQualitative(false)
      .setDomain(CoreMetrics.DOMAIN_SIZE)
      .create[java.lang.Integer]()
}
