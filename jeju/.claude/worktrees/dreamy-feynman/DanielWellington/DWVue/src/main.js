import { createApp } from 'vue'
import { createpinia } from 'pinia'
import App from './App.vue'
import router from './router'

createApp(App).use(createpinia()).use(router).mount('#app')

router.isReady().then(()=> {
    App.mount('#app')
})