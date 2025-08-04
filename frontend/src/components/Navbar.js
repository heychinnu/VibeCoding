import React, { useState } from 'react';
import {
  AppBar,
  Toolbar,
  Typography,
  Button,
  IconButton,
  Menu,
  MenuItem,
  Avatar,
  Box,
  useTheme,
  useMediaQuery
} from '@mui/material';
import {
  Restaurant as RestaurantIcon,
  AccountCircle,
  Dashboard,
  MenuBook,
  Explore,
  Add,
  Menu as MenuIcon
} from '@mui/icons-material';
import { useNavigate, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
  const { user, logout, isAuthenticated } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('md'));
  
  const [anchorEl, setAnchorEl] = useState(null);
  const [mobileMenuAnchor, setMobileMenuAnchor] = useState(null);

  const handleProfileMenuOpen = (event) => {
    setAnchorEl(event.currentTarget);
  };

  const handleMenuClose = () => {
    setAnchorEl(null);
    setMobileMenuAnchor(null);
  };

  const handleLogout = () => {
    logout();
    navigate('/');
    handleMenuClose();
  };

  const navigation = [
    { label: 'Dashboard', path: '/dashboard', icon: <Dashboard /> },
    { label: 'Recipes', path: '/recipes', icon: <MenuBook /> },
    { label: 'Restaurants', path: '/restaurants', icon: <RestaurantIcon /> },
    { label: 'Discover', path: '/discover', icon: <Explore /> }
  ];

  const isActive = (path) => location.pathname === path;

  return (
    <AppBar position="fixed" elevation={1}>
      <Toolbar>
        {/* Logo */}
        <Box
          sx={{ display: 'flex', alignItems: 'center', cursor: 'pointer' }}
          onClick={() => navigate('/')}
        >
          <RestaurantIcon sx={{ mr: 1 }} />
          <Typography
            variant="h6"
            component="div"
            sx={{ fontWeight: 600, color: 'white' }}
          >
            Taste
          </Typography>
        </Box>

        <Box sx={{ flexGrow: 1 }} />

        {/* Navigation - Desktop */}
        {!isMobile && isAuthenticated && (
          <Box sx={{ display: 'flex', gap: 1, mr: 2 }}>
            {navigation.map((nav) => (
              <Button
                key={nav.path}
                startIcon={nav.icon}
                onClick={() => navigate(nav.path)}
                sx={{
                  color: 'white',
                  backgroundColor: isActive(nav.path) ? 'rgba(255,255,255,0.1)' : 'transparent',
                  '&:hover': {
                    backgroundColor: 'rgba(255,255,255,0.1)'
                  }
                }}
              >
                {nav.label}
              </Button>
            ))}
          </Box>
        )}

        {/* Auth buttons */}
        {isAuthenticated ? (
          <>
            {!isMobile && (
              <IconButton
                color="inherit"
                onClick={() => navigate('/recipes/new')}
                sx={{ mr: 1 }}
              >
                <Add />
              </IconButton>
            )}
            
            {/* Mobile menu */}
            {isMobile && (
              <IconButton
                color="inherit"
                onClick={(e) => setMobileMenuAnchor(e.currentTarget)}
                sx={{ mr: 1 }}
              >
                <MenuIcon />
              </IconButton>
            )}

            {/* Profile menu */}
            <IconButton
              edge="end"
              onClick={handleProfileMenuOpen}
              color="inherit"
            >
              <Avatar sx={{ width: 32, height: 32, bgcolor: 'secondary.main' }}>
                {user?.username?.charAt(0).toUpperCase()}
              </Avatar>
            </IconButton>

            {/* Profile dropdown */}
            <Menu
              anchorEl={anchorEl}
              open={Boolean(anchorEl)}
              onClose={handleMenuClose}
              transformOrigin={{ horizontal: 'right', vertical: 'top' }}
              anchorOrigin={{ horizontal: 'right', vertical: 'bottom' }}
            >
              <MenuItem onClick={() => { navigate('/profile'); handleMenuClose(); }}>
                Profile
              </MenuItem>
              <MenuItem onClick={handleLogout}>
                Logout
              </MenuItem>
            </Menu>

            {/* Mobile navigation menu */}
            <Menu
              anchorEl={mobileMenuAnchor}
              open={Boolean(mobileMenuAnchor)}
              onClose={handleMenuClose}
            >
              {navigation.map((nav) => (
                <MenuItem
                  key={nav.path}
                  onClick={() => { navigate(nav.path); handleMenuClose(); }}
                >
                  <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                    {nav.icon}
                    {nav.label}
                  </Box>
                </MenuItem>
              ))}
              <MenuItem onClick={() => { navigate('/recipes/new'); handleMenuClose(); }}>
                <Box sx={{ display: 'flex', alignItems: 'center', gap: 1 }}>
                  <Add />
                  Add Recipe
                </Box>
              </MenuItem>
            </Menu>
          </>
        ) : (
          <Box sx={{ display: 'flex', gap: 1 }}>
            <Button color="inherit" onClick={() => navigate('/login')}>
              Login
            </Button>
            <Button
              variant="outlined"
              sx={{
                color: 'white',
                borderColor: 'white',
                '&:hover': {
                  borderColor: 'white',
                  backgroundColor: 'rgba(255,255,255,0.1)'
                }
              }}
              onClick={() => navigate('/signup')}
            >
              Sign Up
            </Button>
          </Box>
        )}
      </Toolbar>
    </AppBar>
  );
};

export default Navbar;