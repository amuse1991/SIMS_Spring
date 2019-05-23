const router = require('express').Router();
const controller = require('./auth.controller');
const jwt = require('jsonwebtoken')

router.post("login",controller.login);

module.exports = router;