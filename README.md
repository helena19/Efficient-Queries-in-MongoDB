# BSC Thesis

In this thesis, we present efficient techniques for inserting data and running queries over
a non-relational database, giving the user the option to encrypt certain fields, the ones
they want, of the document they insert. 
We worked on the Java Driver of a non-relational
database, more specifically the MongoDB, by modifying some of its existing functions and
enhancing it with our own functions in order to achieve encryption of the data. With
the changes we made to the Java Driver, our application now supports the insertion of
documents containing encrypted fields and gives the user the ability to run queries even
about the encrypted fields. In particular, two encryption modes have been implemented:
encryption using SHA-256 and BCrypt encryption. The SHA-256 (256-bit Secure Hash
Algorithm) is based on multiple “rounds” of hashing. BCrypt also relies on a hashing
function, but considered to be a more secure algorithm due to the addition salt, a random
data used in the production of the encrypted data output. In order to encrypt the fields with
the two encryption types we mentioned before, we have utilized the libraries DigestUtils
 and BcryptPasswordEncoder from Spring for SHA-256 and BCrypt encryption respectively.
The main purpose of this thesis is to study the efficiency of inserting data and
running queries on a NoSQL database, with the data containing encrypted fields, compared
to the simple Java Driver that does not support encryption.


First, the algorithm used in order to efficiently insert documents into the database using
the SHA-256 encryption on the requested fields is quoted and analyzed. A key element of
the implementation is that we provide the user with the ability to define the specific fields
they want to appear on the database as encrypted fields. In addition to this, the algorithm
developed in order to achieve efficient querying on the database for document fields that
are encrypted, and its implementation is analyzed. The algorithm we developed resulted
in having an insertion time with encryption and a querying process time (with the data
being encrypted) that competes with the time the existing Java Driver needs to complete
those processes.


Then, the algorithm used in order to support BCrypt encryption is presented and analyzed.
Again, the user is able to specify the fields they want to encrypt with the BCrypt algorithm.
Below, the most efficient algorithm for querying the base for this encryption mode is shown
and the factors that make it different from the SHA-256 are analyzed.
Subsequently, time measurements of both simple, basic queries and more complicated
queries, such as using embedded fields, are presented. The results are compared in two
ways: firstly, there is the comparison between the two encryption methods, SHA-256 and
BCrypt, and secondly, the comparison between our approach and the existing insert, find
etc methods of Mongo Driver library. At the same time, trade-offs are analyzed in each
case.
