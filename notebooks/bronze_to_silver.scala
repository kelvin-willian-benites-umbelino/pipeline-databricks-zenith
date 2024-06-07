// Databricks notebook source
// MAGIC %python
// MAGIC dbutils.fs.ls("/mnt/dados/bronze")

// COMMAND ----------

val path1 = "dbfs:/mnt/dados/bronze/dataset_zenith"
val df = spark.read.format("delta").load(path1)

val dados_detalhados = df.select("anuncio.*", "anuncio.endereco.*")
val df_silver = dados_detalhados.drop("caracteristicas", "endereco")
display(df_silver)

val path2 = "dbfs:/mnt/dados/silver/dataset_zenith"
df_silver.write.format("delta").mode("overwrite").save(path2)


// COMMAND ----------



// COMMAND ----------


