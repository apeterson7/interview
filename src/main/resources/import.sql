-- insert into book values(1, 'The Tartar Steppe');
-- insert into book values(2, 'Poem Strip');
-- insert into book values(3, 'Restless Nights: Selected Stories of Dino Buzzati');


-- insert into question values(1, 'answer1', 'junior', 'Greatest Common Factor',  -1, 'question1', 'single');
-- insert into question values(2,  'answer2', 'mid level',  'Fibonacci', -1, 'question2', 'single');
-- insert into question values(3, 'answer3','senior','Traveling Salesman', -1,'question3','single');
--
-- insert into candidate values(1, 'candidate@candidate.com', 'joe','plumber', 'excellent','password','new', 'candidate1');
-- insert into candidate values(2, 'candidate2@candidate.com', 'alex','peterson', 'decent', 'password2','new','candidate2');

-- Java
insert into question values(1, 'Reference', 'junior', 'Java Method Parameter',  -1, 'Method parameter passed by reference or value.', 'mutliple');
insert into question values(2,  'answer2', 'mid level',  'Functional Interfaces', -1, 'Define functional interfaces in Java and why do we need them.', 'single');
insert into question values(3, 'answer','junior','Serialization/Deserialization', -1,'Define serialization and deserialization of custom objects.','single');
insert into question values(4, 'answer','junior','Java Try/Catch block', -1,'Try block without catch, is it possible and how?','single');
insert into question values(5, 'answer','junior','Marker Interfaces', -1,'Static classes vs non-static nested classes.','single');
insert into question values(6, 'answer','mid level','Java concurrency', -1,'Difference between invoking Thread.start() vs Thread.run() methods?','single');
insert into question values(7, 'answer','junior','Java persistence', -1,'Difference between Volatile vs Transient variable types.','single');

-- Spring Questions:
insert into question values(8, 'answer','junior','Dependency Injection', -1,'What are the different types of Dependency Injection (IOC)?','single');
insert into question values(9, 'answer','junior','Bean Scopes', -1,'What are the various Bean scopes?','single');
insert into question values(10, 'answer','junior','Bean Lifecycle', -1,'What is the typical Bean life cycle in spring bean factory container?','single');
insert into question values(11, 'answer','junior','Aspect/JointPoint/Advice', -1,'What is an Aspect, JointPoint and Advice?','single');

-- Design:
insert into question values(13, 'answer','junior','Marker Interfaces', -1,'Explain Open-Close policy.','single');
insert into question values(14, 'answer','junior','Marker Interfaces', -1,'Dependency Inversion Principle.','single');
insert into question values(15, 'answer','junior','Marker Interfaces', -1,'Structural vs Creational vs Behavioral design patterns which they have used in their projects.','single');
insert into question values(16, 'answer','junior','Marker Interfaces', -1,'What would be the result of executing SingletonClass.getInstance().clone()?','single');

-- SQL:
insert into question values(17, 'answer','junior','SQL Unions', -1,'Difference between UNION vs UNION ALL?','single');
insert into question values(18, 'answer','junior','Second highest query', -1,'Provide a query for second highest salary retrieval from Employee table (single record).','single');
insert into question values(19, 'answer','junior','SQL Clauses', -1,'Difference between WHERE clause vs HAVING clause','single');
insert into question values(20, 'answer','junior','DDL', -1,'Delete vs Truncate table records?','single');

-- Web Concepts:
insert into question values(21, 'answer','junior','Forward vs. Redirect', -1,'What is the difference between Forward and Redirect?','single');
insert into question values(22, 'answer','junior','HTTP', -1,'Different types of HTTP requests?','single');
insert into question values(23, 'answer','junior','Angular1', -1,'What is data binding in Angular and how does it relate to MVC architecture?','single');
insert into question values(24, 'answer','junior','Angular2', -1,'Define factory, service and directive in Angular?','single');
insert into question values(25, 'answer','junior','AuthN vs. AuthZ', -1,'Difference between Authorization and Authentication?','single');
insert into question values(26, 'answer','junior','Marker Interfaces', -1,'Define marker interfaces and why do we need those.','single');

-- SOAP / REST:
insert into question values(27, 'answer','junior','Soap vs. Rest', -1,'What are the primary differences between message structures and communication protocols for Soap and Rest?','single');
insert into question values(28, 'answer','junior','Soap Protocol', -1,'Describe the difference between WSDL and WADL.','single');
insert into question values(29, 'answer','junior','Soap/Rest Authentication', -1,'Describe authentication in Soap and Rest communications','single');

-- Messaging Structures
insert into question values(30, 'answer','junior','Messaging Structure', -1,'Compare and contrast XML and JSON','single');
insert into question values(31, 'answer','junior','XML Parsing', -1,'List and describe various kinds of XML parsers / methods','single');
insert into question values(32, 'answer','junior','DTD vs XSD', -1,'Compare DTD and XSD.','single');

-- AWS
insert into question values(33, 'answer','junior','AWS Security Groups', -1,'What are SecurityGroups?','single');
insert into question values(34, 'answer','junior','EC2 vs ECS', -1,'Describe the difference between EC2 and ECS','single');
insert into question values(35, 'answer','junior','KMS encryption', -1,'Describe KMS encryption.','single');
insert into question values(36, 'answer','junior','Lambdas', -1,'Describe the advantages and drawbacks of Lambdas.','single');
insert into question values(37, 'Monitor, Log, Provision','junior','AWS DevOps', -1,'Describe Cloudwatch, Cloudtrail, CloudFormation.','single');
insert into question values(38, 'answer','junior','SNS/SQS', -1,'Describe the purpose and differences between SNS and SQS','single');
