<h3><I>Event Alert System</I></h3>

<p><b>To build and run docker Image : </b></p>

<ol>
<li>cd {project-directory}</li>
<li>docker build -t event-alert-system . </li>
<li>docker run -p [port-num] event-alert-system </li>
</ol>


<p><b>To build and run jar file : </b></p>

<ol>
<li>cd {project-directory}</li>
<li>mvn clean install </li>
<li>java -jar cd/target/[event-alert-system-snapshot].jar </li>
</ol>