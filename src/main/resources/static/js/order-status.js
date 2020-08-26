let orderStatus;
let statusParagraph = document.getElementById("statusParagraph");
let progressBar = document.getElementById("progressBar");

const updateStatus = async () => {
    const response = await fetch(window.location.href + '/status');
    orderStatus = await response.json();

    switch (orderStatus) {
        case "WAITING":
            statusParagraph.textContent = "Ожидание свободного дрона";
            progressBar.style.width = "33%";
            break;
        case "IN_PROGRESS":
            statusParagraph.textContent = "В процессе выполнения";
            progressBar.style.width = "66%";
            break;
        case "COMPLETE":
            statusParagraph.textContent = "Выполнен";
            progressBar.style.width = "100%";
            break;
        default:
            statusParagraph.textContent = "Отменён";
            progressBar.style.width = "0%";
    }
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

async function loop() {
    do {
        await updateStatus();
        await sleep(2000);
    } while (orderStatus != "COMPLETE")
}

loop();
