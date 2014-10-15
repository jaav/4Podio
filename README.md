README for 4podio
==========================

To run tests use:

mvn test -Dtest=test_name -Dpodio.demo.username=your_podio_username -Dpodio.demo.password=your_podio_password -Dpodio.secret=client_secret

OR

mvn test -Dtest=test_name -Dpodio.demo.console=true -Dpodio.secret=client_secret

to enter credentials via command prompt.