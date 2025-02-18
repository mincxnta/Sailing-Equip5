window.onload = function(){
const wave = document.getElementById("wave");
let step = 0;

function animateWave() {
    step += 0.03;
    const amp = 20;

    const newD = `
        M0,${160 + Math.sin(step) * amp}
        C320,${240 + Math.cos(step * 2) * amp},640,${80 + Math.sin(step * 3) * amp},960,${160 + Math.cos(step) * amp}
        C1280,${240 + Math.sin(step * 2) * amp},1440,${80 + Math.cos(step * 3) * amp},1440,160
        L1440,320 L0,320 Z
    `;

    wave.setAttribute("d", newD);
    requestAnimationFrame(animateWave);
}

animateWave();
}