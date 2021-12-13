<template>
  <section>
    <div class="auth-wrapper">
      <div class="auth-inner">
        <div class="help--wrapper">
          <div class="info--title">
            <div class="title--line"></div>
            <h2>Помощь</h2>
            <div class="title--line"></div>
          </div>
          <div class="help--main--description">
            <div class="sub--border">
              <p>Реквизиты в Республике Беларусь: </p>
              <p> ООО "Стрела" </p>
              <p> Свидетельство о государственной регистрации выдано Минским горисполкомом от
                24.11.2021. </p>
              <p>УНП 193125500 </p>
              <p>РБ, 220073, г. Минск, ул. Ольшевского, д.24, оф. 403 п.16-49</p>
            </div>
          </div>
          <div class="help--main--wrapper">
            <div class="help--main--nav--wrapper">
              <div class="help--main--nav">
                <button class="help--main--link" @click="toContacts"><p>контакты</p></button>
                <button class="help--main--link" @click="toPartners"><p>партнеры</p></button>
                <button class="help--main--link" @click="toTerms"><p>условия</p></button>
                <button class="help--main--link" @click="toDrivers"><p>водителям</p></button>
                <button class="help--main--link" @click="toCarriers"><p>перевозчикам</p></button>
              </div>
            </div>
          </div>
          <div v-if="isHomePage" class="container mt-10">

          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script>
import { onMounted, ref } from "vue";
import { useRouter } from "vue-router";

export default {
  name: "help",
  setup() {
    const router = useRouter();
    const isHomePage = ref(true);

    const toDrivers = () => {
      isHomePage.value = false;
      router.push('for-drivers');
    }

    const toPartners = () => {
      isHomePage.value = false;
      router.push('partners');
    }

    const toTerms = () => {
      isHomePage.value = false;
      router.push('terms');
    }

    const toCarriers = () => {
      isHomePage.value = false;
      router.push('for-carriers');
    }

    const toContacts = () => {
      isHomePage.value = false;
      router.push('contacts');
    }

    onMounted(()=> {
      isHomePage.value = true;
    })

    return {
      isHomePage,
      toDrivers,
      toCarriers,
      toTerms,
      toContacts,
      toPartners
    }
  }
};
</script>

<style scoped>
.info--title {
  font-size: 36px;
  text-transform: uppercase;
  text-align: center;
  display: flex;
  justify-content: center;
  padding-top: 110px;
}


h2 {
  display: block;
  font-size: 1.0em !important;
  font-weight: bold;
  margin-top: -20px;
  margin-bottom: 40px !important;
  font: inherit !important;
}

.help--main--nav--wrapper .help--main--nav {
  max-width: 1300px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-top: 50px;
  text-align: center;
}

.mt-10 {
  padding-bottom: 236px;
}

.info--title {
  font-size: 36px;
  text-transform: uppercase;
  text-align: center;
  display: flex;
  justify-content: center;
  padding-top: 150px;
}

.help--main--description {
  margin-top: 60px;
  max-width: 880px;
  line-height: 24px;
  font-size: 20px;
}

p {
  font-variation-settings: "wdth" 75;
  font: 18px Bahnschrift;
  padding-left: 2px !important;
  color: black;
}

.sub--border {
  border-left: 4px solid;
  color: black;
}

.help--main--nav--wrapper .help--main--nav .help--main--link {
  width: 241px;
  height: 60px;
  background: #FFFFFF;
  box-shadow: 0px 2px 2px 2px rgb(0 0 0 / 10%);
  border-radius: 8px;
}

.title--line {
  position: relative;
  width: 135px;
  height: 3px;
  background: #ee1c1c;
  margin: 5px 35px;
  vertical-align: center;
}

.booking--find--wrapper .booking--form--container form {
  display: flex;
}

.help--main--nav--wrapper .help--main--nav {
  max-width: 1300px;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-top: 50px;
  text-align: center;
}

.help--wrapper {
  max-width: 1300px;
  margin: 0 auto;
  margin-bottom: -90px;
}

.help--main--description {
  margin-top: 60px;
  max-width: 880px;
  line-height: 24px;
  font-size: 20px;
}

.help--main--description p {
  padding-left: 60px;
}

.help--main--description .sub--border {
  border-left: 4px solid #ee1c1c;
}

.help--main--nav--wrapper .help--main--nav .help--main--link {
  width: 241px;
  height: 60px;
  background: #FFFFFF;
  box-shadow: 0px 2px 2px 2px rgba(0, 0, 0, 0.1);
  border-radius: 8px;
}

.help--main--nav--wrapper .help--main--nav .help--main--link p {
  text-decoration: none;
  text-transform: none;
  margin-left: 0px;
  margin-top: 15px;
}

.help--main--nav--wrapper a {
  text-decoration: none;
  font-size: 24px;
  color: #000;
}

p {
  margin-top: 10px;
  margin-bottom: 1rem;
  margin-left: 87px;
}
</style>