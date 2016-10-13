package com.outr.lucene4s.field.value.support

import com.outr.lucene4s.field.Field
import org.apache.lucene.document.{SortedDocValuesField, Field => LuceneField}
import org.apache.lucene.index.IndexableField
import org.apache.lucene.search.SortField
import org.apache.lucene.search.SortField.Type
import org.apache.lucene.util.BytesRef

object StringValueSupport extends ValueSupport[String] {
  override def toLucene(field: Field[String], value: String): IndexableField = {
    new LuceneField(field.name, value, field.fieldType.lucene())
  }

  override def toSortedField(field: Field[String], value: String): Option[SortedDocValuesField] = {
    Some(new SortedDocValuesField(field.name, new BytesRef(value)))
  }

  override def fromLucene(field: IndexableField): String = field.stringValue()

  override def sortFieldType: Type = SortField.Type.STRING
}