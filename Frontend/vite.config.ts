import { fileURLToPath, URL } from 'node:url'
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  // ESTA ES LA MAGIA PARA SCSS:
  css: {
    preprocessorOptions: {
      scss: {
        additionalData: `
          @use "@/styles/_colores.scss" as *;
          @use "@/styles/_mixins.scss" as *;
          @use "@/styles/_tipografias.scss" as *;
        `
      }
    }
  }
})