package com.outr.lucene4s.facet

import org.apache.lucene.document.Document
import org.apache.lucene.facet.{FacetField => LFF}

class FacetValue(val field: FacetField, val path: String*) {
  private[lucene4s] def write(document: Document): Unit = {
    val updatedPath = if (field.hierarchical) path.toList ::: List("$ROOT$") else path
    document.add(new LFF(field.name, updatedPath: _*))
  }
}