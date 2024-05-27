// document.getElementById('problemDiv').addEventListener('click', function() {
//     var problemId = this.getElementsByTagName('td')[0].id;
//     var parts = problemId.split('-');
//     var url = 'https://codeforces.com/problemset/problem/' + parts[1] + '/' + parts[2];
//     window.open(url, '_blank');
// });

function openLink(contestId, problemId) {
    var url = 'https://codeforces.com/problemset/problem/' + contestId + '/' + problemId;
    window.open(url, '_blank');
}