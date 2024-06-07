// Databricks notebook source
// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt/dados/inbound")

// COMMAND ----------

val path = "dbfs:/mnt/dados/inbound/dados_brutos.json"
val dados = spark.read.json(path)

// COMMAND ----------

val dados_ = dados.drop("imagens", "usuario")
display(dados_)


// COMMAND ----------

import org.apache.spark.sql.functions.col
val df_bronze = dados_.withColumn("id", col("anuncio.id"))
display(df_bronze)

// COMMAND ----------

val path = "dbfs:/mnt/dados/bronze/dataset_zenith"
df_bronze.write.format("delta").mode(SaveMode.Overwrite).save(path)
