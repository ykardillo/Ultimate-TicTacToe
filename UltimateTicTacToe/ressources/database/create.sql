/**
 * Author:  g45682
 * Created: 28-avr.-2019
 */

create table player (
  id int not null,
  pseudo varchar(30) not null,
  nbWins int not null,
  nbLoses int not null,
  nbDraws int not null,
  nbUsedJoker int not null,
  constraint idPk primary key(id),
  constraint pseudoUnique unique(pseudo)
);