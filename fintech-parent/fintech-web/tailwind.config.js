const {nextui} = require('@nextui-org/theme');
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./node_modules/@nextui-org/theme/dist/components/(avatar|navbar).js"
].js"
],
  theme: {
    extend: {},
  },
  plugins: [nextui()],
}


