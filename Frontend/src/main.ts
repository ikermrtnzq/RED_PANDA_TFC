// IMPORTACIONES DE LAS LIBRERIAS NECESARIAS
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import { createI18n } from 'vue-i18n'

// IMPORTACIONES DEL COMPONENTE PRINCIPAL Y ESTILOS GLOBALES
import App from './App.vue'
import router from './router/index'
import '@/styles/main.scss'

// IMPORTACION DE LA CONFIGURACION DE IDIOMAS
import es from "./locales/es.json"
import en from '@/locales/en.json'
import zh from '@/locales/zh.json'
import fr from '@/locales/fr.json'
import ja from '@/locales/ja.json'
import ca from '@/locales/ca.json'

const idiomaGuardado = localStorage.getItem('idioma-preferido') || 'es'

const i18n = createI18n({
    legacy: false,
    locale: idiomaGuardado,
    fallbackLocale: 'es',
    messages: {
        es,
        en,
        zh,
        fr,
        ja,
        ca
    }
})

// ARRANQUE DE APLICACION
const app = createApp(App)

// ACTIVACION DE LOS ARCHIVOS
app.use(createPinia())
app.use(router)
app.use(i18n)

app.mount('#app')
