console.log("In script");

const url = "http://localhost:8080/";
const bookResult = document.querySelector('.book-result');
const userResult = document.querySelector('.user-result');

let bookArr;
let userArr;

getBooks();
getUsers();
/****************************Display books************************************* */
async function getBooks() {
    let response = await fetch(url + '/book/getall', {
        method: 'GET',
    });
    bookArr = await response.json();
    updateView();
}


function showBooks() {
    if (bookArr === undefined)
        return;
    bookResult.innerHTML = `<h2> Books </h2>`;

    bookArr.forEach(el => {
        let bookstr = document.createElement('div');
        bookstr.setAttribute('class', 'bookstr');

        let bookId = document.createElement('div');
        bookstr.appendChild(bookId);
        bookId.innerHTML = el.id;

        let bookTitle = document.createElement('div');
        bookstr.appendChild(bookTitle);
        bookTitle.innerHTML = "\"" + el.title + "\"";

        let bookAuthor = document.createElement('div');
        bookstr.appendChild(bookAuthor);
        bookAuthor.innerHTML = el.author;

        let bookYear = document.createElement('div');
        bookstr.appendChild(bookYear);
        bookYear.innerHTML = el.issueYear;

        let bookBysy = document.createElement('div');
        bookstr.appendChild(bookBysy);

        if (el.busy) {
            bookBysy.innerHTML = "Busy";
            bookBysy.setAttribute('class', 'busy');
        }
        else {
            bookBysy.innerHTML = "Free";
            bookBysy.setAttribute('class', 'free');
        }
        // console.log(el);
        bookResult.appendChild(bookstr);
    });
}


async function getUsers() {
    let response = await fetch(url + '/user/getall', {
        method: 'GET',
    });
    userArr = await response.json();
    updateView();
}

function showUsers() {
    if (userArr === undefined)
        return;

    userResult.innerHTML = `<h2> Users </h2>`;

    userArr.forEach(el => {
        let clBlock = document.createElement('div');
        clBlock.setAttribute('class', 'clBlock');

        let clstr = document.createElement('div');
        clstr.setAttribute('class', 'clstr');

        let bkStr = document.createElement('div');
        bkStr.setAttribute('class', 'bkStr');


        let id = document.createElement('div');
        clstr.appendChild(id);
        id.innerHTML = el.id;

        let firstName = document.createElement('div');
        clstr.appendChild(firstName);
        firstName.innerHTML = el.name;

        let lastName = document.createElement('div');
        clstr.appendChild(lastName);
        lastName.innerHTML = el.surname;

        let email = document.createElement('div');
        clstr.appendChild(email);
        email.innerHTML = el.email;

        if (el.books.length > 0) {
            let bkStrContent = '&bull; Holds books: ';

            for (i = 0; i < el.books.length; i++) {
                bkStrContent += el.books[i].id;
                bkStrContent += ", ";
            }
            bkStr.innerHTML = bkStrContent;
        }
        clBlock.appendChild(clstr);
        clBlock.appendChild(bkStr);
        userResult.appendChild(clBlock);
        // console.log(el);
    });
}

async function removeAllBooks() {
    let response = await fetch(url + '/book/remall', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
    });
    getBooks();
    updateViewDelayed();
}

async function removeAllUsers() {
    let response = await fetch(url + '/user/remAll', {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
    });
    getUsers();
    updateViewDelayed();
}

const bookIdForm = document.querySelector('.bookId');
const userIdForm = document.querySelector('.userId');
async function processTakeForm() {
    let tBookId = Number(parseInt(bookIdForm.textContent));
    let tuserId = Number(parseInt(userIdForm.textContent));

    if (isNaN(tBookId) || isNaN(tuserId)) {
        alert("Only digits in range of Data");
        return;
    }

    let response = await fetch(url + 'action/take', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `bookId=${tBookId}&userId=${tuserId}`

    });
    let ans = await response.text();
    if (ans.includes("not exist"))
        alert(ans);
    getBooks();
    getUsers();

}

async function processRetForm() {
    let tBookId = Number(parseInt(bookIdForm.textContent));
    let tUserId = Number(parseInt(userIdForm.textContent));

    if (isNaN(tBookId) || isNaN(tUserId)) {
        alert("Only digits in range of Data");
        return;
    }
    let response = await fetch(url + '/action/return', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `bookId=${tBookId}&userId=${tUserId}`

    });
    let ans = await response.text();
    if (ans.includes("not exist"))
        alert(ans);
    getBooks();
    getUsers();
}

function updateView() {
    setTimeout(() => { showUsers(); showBooks(); }, 200);
}

function     updateViewDelayed() {
        setTimeout(() => { getUsers(); getBooks(); }, 1200);
}
