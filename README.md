Norint paleisti web servisą reikia paleisti šias komandas:

docker image build -t todolist .

docker container run --name listservice -p 80:5000 -d todolist

Tada galima eiti:

/todos - su GET pamatyti visus įrašus su POST pridėti naują 

/todos/id su GET pamatyti specifini įrašą(su DELETE ištrinti specifinį įrašą

/todos/priority/number - gražina visus įrašus kurių priority yra number
su PUT koreguoti esantį įrašą
