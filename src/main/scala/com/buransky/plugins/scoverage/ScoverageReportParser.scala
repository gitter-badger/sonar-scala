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
package com.buransky.plugins.scoverage

import com.buransky.plugins.scoverage.pathcleaner.PathSanitizer

/**
 * Interface for Scoverage report parser.
 *
 * @author Rado Buransky
 */
trait ScoverageReportParser {
  def parse(reportFilePath: String, pathSanitizer: PathSanitizer): ProjectStatementCoverage
}

/**
 * Common Scoverage exception.
 *
 * @author Rado Buransky
 */
case class ScoverageException(message: String, source: Throwable = null) extends Exception(message, source)
