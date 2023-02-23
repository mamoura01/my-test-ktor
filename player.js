db = connect( 'mongodb://localhost/players' );

db.player.drop()

db.player.insertMany( [{
  "pseudo": "pierre",
  "score": 1
},{
  "pseudo": "paul",
  "score": 5
},{
  "pseudo": "frank",
  "score": 6
},{
  "pseudo": "simon",
  "score": 4
},{
  "pseudo": "mathieu",
  "score": 7
},{
  "pseudo": "sebastien",
  "score": 3
},{
  "pseudo": "charles",
  "score": 8
},{
  "pseudo": "nicolas",
  "score": 2
},{
  "pseudo": "louis",
  "score": 9
},{
  "pseudo": "jack",
  "score": 0
}] )

