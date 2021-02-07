let names = ["Berny", "Jack", "Inga", "Tanya", "Manya", "Tolya", "Stewen", "Mark", "George", "Bob", "Boris", "Lara", "Anatol", "Demis", "Billy", "Villy", "Bill", "Tad", "Uggo", "Abram", "Monya"];
let surnames = ["Bernson", "Jackson", "Ingmar", "Tanny", "Mikles", "King", "Stewenson", "Bush", "Gregor", "Bobson", "Villis","Laren", "Finger", "Borland", "Biller", "Kacman", "Goldman", "Brotberg", "Uggo"];
let wordsN = ["soil", "shark", "cow", "dog", "bill", "house", "wind", "ocean", "sea", "boddy", "fellow", "deer", "hat", "roof", "crime", "kiss", "mistake", "trust", "gerl", "cat", "will", "driver"];
let wordsA = ["dark", "slow", "mad", "bad", "silly", "fast", "red", "sunny", "funny", "black", "white", "deep", "rusty", "dumb", "prime", "bold", "owfull", "running","folling", "my", "sweet", "shaggy", "crazy"];
let mail = ["gmail.com", "yahoo.com", "msoft.us", "live.com", "i.ua"];

function getName(){
    let item = names[Math.floor(Math.random() * names.length)];

    return item;
}
function getSurename(){
    let item = surnames[Math.floor(Math.random() * surnames.length)];
    return item;
}

function getFullName(){
return getName() + " " + getSurename();
}

function getTitle(){
    let item1 = wordsA[Math.floor(Math.random() * wordsA.length)];
    let item2 = wordsN[Math.floor(Math.random() * wordsN.length)];
    return capitalizeFirstLetter(item1) + " " + capitalizeFirstLetter(item2);
}

function getYear(){
    return Math.floor(Math.random() * 120) + 1900;  
}

function getMail(name){
    return name.replace(/ /g, '_') + Math.floor(Math.random() * 299) + '@' + mail[Math.floor(Math.random() * mail.length)];
}

function getPrice(max){
    return Math.floor(Math.random() * max);  
}


function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

//   let ttt = getFullName();
//   console.log(ttt);
//   console.log(getMail(ttt));
//   console.log(getTitle());
//   console.log(getYear());
//   console.log('');

//   ttt = getFullName();
//   console.log(ttt);
//   console.log(getMail(ttt));
//   console.log(getTitle());
//   console.log(getYear());
//   console.log('');

//   ttt = getFullName();
//   console.log(ttt);
//   console.log(getMail(ttt));
//   console.log(getTitle());
//   console.log(getYear());
//   console.log('');










