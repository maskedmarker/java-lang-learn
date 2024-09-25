# 关于jdbc的一些记录



## 关键点:

### DriverManager
The javax.sql.DataSource interface, new in the JDBC 2.0 API, provides another way to connect to a data source.
The use of a DataSource object is the preferred means of connecting to a data source.
connection的工厂类

### DataSource
An alternative to the DriverManager facility, a DataSource object is the preferred means of getting a connection.
An object that implements the DataSource interface will typically be registered with a naming service based on the Java™ Naming and Directory (JNDI) API.
connection的工厂类,是DriverManager升级版

