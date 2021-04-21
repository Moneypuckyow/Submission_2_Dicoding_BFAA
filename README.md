# Submission_2_Dicoding_BFAA


![image](https://user-images.githubusercontent.com/67593237/115465847-aef2f580-a1db-11eb-8183-5c7c017cb57f.png)
![Screenshot (52)](https://user-images.githubusercontent.com/67593237/115450974-0c317b80-a1c9-11eb-8ecb-70ee9437696c.png)
Di main activity untuk penggunaan search view function sudah sesuai dengan yang diajarkan oleh modul dicoding.
yang mana untuk mendapatkan data username users menggunakan function searchUsersData()

![image](https://user-images.githubusercontent.com/67593237/115465983-e19cee00-a1db-11eb-8455-bb7c44b710f7.png)
karena data JSON API nya bersifat array (https://api.github.com/search/users?q={username}) maka untuk mendapatkan data "username" users kita harus mengambil nilai value dari array tersebut yang di inisialisasi dengan "items", lalu mengambil data JSONObject username untuk digunakan di function searchUser() sehingga bisa mencari data username users.

------

![image](https://user-images.githubusercontent.com/67593237/115466052-fe392600-a1db-11eb-888f-0af16a77a364.png)
![device-2021-04-20-135919](https://user-images.githubusercontent.com/67593237/115469230-ba94eb00-a1e0-11eb-955a-6bcfb30254ef.png)
data username users juga dikirim kan ke function getDetailUsers() untuk mendapatkan data users yang lain seperti data company, location, repository, followers, dan following users melalui (https://api.github.com/users/{username}), yang mana data tersebut bakal digunakan di list recycleview search users.

 ------

![image](https://user-images.githubusercontent.com/67593237/115466111-1446e680-a1dc-11eb-8e41-5c33d3bfad57.png)
![image](https://user-images.githubusercontent.com/67593237/115452253-a80fb700-a1ca-11eb-9396-b5bf1f8f329d.png)
![image](https://user-images.githubusercontent.com/67593237/115466177-2de82e00-a1dc-11eb-88c7-ff222f50a746.png)
untuk bagian recyclerview saya sudah menggunakannya sesuai dengan yang di ajarkan di modul sekaligus menggunakan function onItemClicked() untuk mengirimkan data yang di klik, ke function showDetailPage() yang mana function tersebut digunakan untuk pindah activity dan menyimpan value username yang dipilih di dalam konstan value di variabel "EXTRA_USERS".

-------

![image](https://user-images.githubusercontent.com/67593237/115466493-a5b65880-a1dc-11eb-946d-ee3f700b0b5c.png)

dibagian ListUsersAdapter() data yang ditampilkan di listView adalah data username, name, avatar, followers, dan following users.
di dalam submission ini saya hanya menggunakan 1 adapter untuk menampilkan data di listView atau recyclerview, sedangkan untuk bagian DetailActivity.kt tidak menggunakan adapter list view kecuali untuk sectionpager adapter.


![image](https://user-images.githubusercontent.com/67593237/115466821-13fb1b00-a1dd-11eb-9630-883dc9d675b9.png)
![image](https://user-images.githubusercontent.com/67593237/115466944-3ab95180-a1dd-11eb-952f-3f324af0112b.png)
![image](https://user-images.githubusercontent.com/67593237/115467172-8d930900-a1dd-11eb-89df-3dd0f081ccc2.png)
![image](https://user-images.githubusercontent.com/67593237/115467202-9c79bb80-a1dd-11eb-961c-2aa7f78e86f2.png)
berikut pengimplementasian Tab Pager sesuai dengan yang ada di modul.

![image](https://user-images.githubusercontent.com/67593237/115467334-d3e86800-a1dd-11eb-9bed-0066f0d70d80.png)
![image](https://user-images.githubusercontent.com/67593237/115467458-06926080-a1de-11eb-8237-a09ff093fdc6.png)

dengan mengambil data yang dikirimkan dari mainActivity lalu di masukan ke constan variable di EXTRA_USERS. data tersebut digunakan di function getData() untuk mendapatkan data detail dari users yang dipilih.


![image](https://user-images.githubusercontent.com/67593237/115467683-5ffa8f80-a1de-11eb-8625-56d676a4c27a.png)

untuk bagian FollowersFragment dan FollowingFragment kurang lebih sama seperti di main activity yang mana kelas ini menampilkan data followers dan following users yang dipilih dengan menggunakan data API yang berbeda di (https://api.github.com/users/{username}/followers" dan "https://api.github.com/users/{username}/following", adapter yang digunakan juga sama karena data yang ditampilkan juga berupa username, name, following, followers, dan avatar.

