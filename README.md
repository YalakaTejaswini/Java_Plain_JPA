**1. What is JPA?**
Simple definition

JPA (Java Persistence API) is a Java specification that provides a standard way to store Java objects in a relational database and retrieve them later.

The important word here is specification.

JPA itself is not an implementation. It defines rules/interfaces that a JPA provider implements.

For example:
Your Java Application
        ↓
       JPA
        ↓
JPA Provider
        ↓
     MySQL
    Popular JPA providers include:

Hibernate
EclipseLink

So when you write JPA code, something like Hibernate usually works behind the scenes to actually communicate with the database.

Why is it called Persistence?

Persistence means:

Making Java object data permanently stored in a database.

For example:
Student student = new Student();

student.setName("Teju");
student.setAge(22);
This object exists in Java memory.

If the application stops, the object disappears.

But if we save it into MySQL:

Java Object
     ↓
Database
     
Teju | 22

the data remains even after the application stops.

That's persistence.

**2. Why do we use JPA?**

To understand this properly, first look at how we traditionally work with a database using JDBC.

Suppose we want to insert a student.

With JDBC, we might write:
String sql =
    "INSERT INTO student(name, age) VALUES (?, ?)";

PreparedStatement ps =
    connection.prepareStatement(sql);

ps.setString(1, "Teju");
ps.setInt(2, 22);

ps.executeUpdate();
You have to deal with:

SQL
Connection
PreparedStatement
ResultSet
converting database rows into Java objects
closing resources

JPA tries to make this more object-oriented.
For Example:
Student student = new Student();

student.setName("Teju");
student.setAge(22);

entityManager.persist(student);

That's much closer to how we think in Java:

Create Object
     ↓
Set Data
     ↓
Save Object

instead of manually writing SQL for every basic operation.

JPA helps with:

1. Object → Database
   Student object
      ↓
   JPA
      ↓
student table
2. Database → Object

student table
      ↓
   JPA
      ↓
Student object

3. Less database-related boilerplate

Instead of manually handling many JDBC operations for basic CRUD, JPA provides APIs such as:

persist()
find()
merge()
remove()
Example

Suppose the database contains:

student
------------------
id   name    age
1    Teju    22
2    Ravi    23

JPA can retrieve student 1 as a Java object:

Student student =
    entityManager.find(Student.class, 1);

Conceptually:

Database
   ↓
id = 1
   ↓
JPA
   ↓
Student object
   ↓
student.getName()

3. What is ORM?

ORM stands for:

Object Relational Mapping

This sounds complicated, but the idea is simple.

Java works with objects.

Relational databases work with tables and rows.

ORM is the technique that connects these two worlds.

Without ORM

Suppose Java has:

Student student = new Student();
student.setName("Teju");
student.setAge(22);

And MySQL has:
student table

id | name | age
----------------
1  | Teju | 22

Java sees:

Student Object

Database sees:

Table
Row
Columns

ORM maps them.

With ORM
Java                         Database

Student class       →       student table

Student object      →       row

name field          →       name column

age field           →       age column

So:

Java Object
     ↕
    ORM
     ↕
Database Table
Simple mapping example

Java:
@Entity
public class Student {

    @Id
    private int id;

    private String name;

    private int age;
}

Database:

student
----------------
id
name
age

ORM understands that:

Student class
       ↓
student table

and:

student.id
       ↓
Student.id

student.name
       ↓
Student.name

student.age
       ↓
Student.age

That's Object Relational Mapping.

JPA and ORM — are they the same?

No.

This is an important interview question.

JPA

JPA is a specification/API for persistence.

ORM

ORM is a concept/technique for mapping Java objects to relational database tables.

Hibernate

Hibernate is an implementation/tool that can implement JPA and provides ORM functionality.

Think of it like this:

             JPA
       (Specification)
              ↓
          Hibernate
       (Implementation)
              ↓
             ORM
     Object ↔ Database

More accurately, Hibernate is an ORM framework and a JPA provider.

