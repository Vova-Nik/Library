
const dburl = "http://localhost:8080/";


async function addBook(book) {
    let response = await fetch(dburl + '/book/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(book)
    });
    let result = await response.json();
    // console.log('Add book - ', result);
}

async function addUser(user) {
    let response = await fetch(dburl + 'user/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json; charset=utf-8'
        },
        body: JSON.stringify(user)

    });
    let result = await response.json();
}

function addRandomBooks() {
    for (i = 0; i < 24; i++) {
        addBook({
            title: getTitle(),
            author: getFullName(),
            issueYear: getYear(),
            price: getPrice(60),
        });
    }
    getBooks();
    updateViewDelayed();
}

function addRandomUsers() {
    for (i = 0; i < 4; i++) {

        let nm = getName();
        let snm = getSurename();
        let user = {
            name: nm,
            surname: snm,
            email: getMail(nm + "_" + snm),
            dateOfBirth: getYear(),
        }
        addUser(user);
    }
    getUsers();
    updateViewDelayed();

}

const addBooksButton = document.querySelector('.add-books-button');
const addUsersButton = document.querySelector('.add-User-button');
const listButton = document.querySelector('.list-button');
